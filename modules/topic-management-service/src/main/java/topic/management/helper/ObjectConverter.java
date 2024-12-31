package topic.management.helper;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.ncema.dto.v1_0.MeetingResponse;
import com.ncema.dto.v1_0.Topic;
import com.ncema.dto.v1_0.TopicAttachment;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import topic.management.constants.TopicConstants;
import topic.management.service.AttachmentService;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Component(service = ObjectConverter.class)
public class ObjectConverter {
    private static final Log _log = LogFactoryUtil.getLog(ObjectConverter.class);

    @Reference
    private AttachmentService _attachmentService;

    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;

    @Reference
    private ObjectDefinitionHelper _objectDefinitionHelper;

    @Reference
    private ObjectDataHelper _objectDataHelper;

    public Topic convertToTopic(ObjectEntry objectEntry) {
        try {
            Topic topic = new Topic();
            topic.setId(objectEntry.getObjectEntryId());
            Map<String, Serializable> values = objectEntry.getValues();
            topic.setTopicTitle((String) values.get("topicTitle"));
            topic.setTopicSubtitle((String) values.get("topicSubtitle"));
            topic.setTopicDescription((String) values.get("topicDescription"));
            topic.setTopicStatus((String) values.get("topicStatus"));
            topic.setTopicAttachments(getTopicAttachments(objectEntry.getObjectEntryId()));
            enrichTopicWithMeetingDetails(topic, objectEntry);

            return topic;
        }
        catch (Exception e) {
            _log.error("Error converting ObjectEntry to Topic: " + objectEntry.getObjectEntryId(), e);
            return null;
        }
    }

    public MeetingResponse convertToMeeting(ObjectEntry objectEntry) {
        if (objectEntry == null) {
            return null;
        }

        try {
            MeetingResponse meeting = new MeetingResponse();
            meeting.setId(objectEntry.getObjectEntryId());
            // Get dynamic values map
            Map<String, Serializable> valuesMap = objectEntry.getValues();
            meeting.setMeetingTitle((String)valuesMap.get("meetingTitle"));

            meeting.setMeetingNumber(GetterUtil.getLong(valuesMap.get("meetingNumber")));
            Date meetingDateTime = (Date)valuesMap.get("meetingDateTime");
            if (meetingDateTime != null) {
                meeting.setMeetingDateTime(meetingDateTime);
            }
            meeting.setMeetingLocation((String)valuesMap.get("meetingLocation"));
            meeting.setMeetingLink((String)valuesMap.get("meetingLink"));

            Long[] participantIds = getParticipantIds(objectEntry);
            meeting.setParticipants(participantIds);

            Topic[] topics = getTopicDetails(objectEntry);
            meeting.setTopics(topics);
            return meeting;
        }
        catch (Exception e) {
            _log.error("Error converting object entry to meeting", e);
            throw new RuntimeException("Error converting meeting data", e);
        }
    }

    //for meeting response to fetch topic details
    private Topic[] getTopicDetails(ObjectEntry meetingEntry) throws PortalException {
        List<Topic> topicList = new ArrayList<>();


        List<ObjectEntry> topicRelationships = ObjectEntryLocalServiceUtil.getManyToManyObjectEntries(
                meetingEntry.getGroupId(),
                _objectDataHelper.getMeetingTopicRelationshipId(TopicConstants.MEETING_OBJECT_KEY),
                meetingEntry.getObjectEntryId(),
                true,
                false,
                null,
                QueryUtil.ALL_POS,
                QueryUtil.ALL_POS
        );

        // Convert each related entry to Topic
        for (ObjectEntry topicEntry : topicRelationships) {
            try {
                Topic topic = convertToTopic(topicEntry);
                if (topic != null) {
                    topicList.add(topic);
                }
            } catch (Exception e) {
                _log.error("Error converting topic: " + e.getMessage(), e);
            }
        }

        return topicList.toArray(new Topic[0]);
    }

    //for meeting response fetch participantsIds
    public Long[] getParticipantIds(ObjectEntry meetingEntry) {
        try {
            ObjectDefinition participantDefinition = _objectDefinitionHelper.getObjectDefinition(
                    meetingEntry.getCompanyId(),
                    TopicConstants.PARTICIPANT_OBJECT_KEY
            );

            // Now get participant entries with correct definition ID
            List<ObjectEntry> participantEntries = _objectEntryLocalService.getObjectEntries(meetingEntry.getGroupId(),
                            participantDefinition.getObjectDefinitionId(),
                            QueryUtil.ALL_POS,
                            QueryUtil.ALL_POS
                    ).stream()
                    .filter(entry -> {
                        Map<String, Serializable> values = entry.getValues();
                        long entryMeetingId = GetterUtil.getLong(values.get("meetingID"));
                        _log.info("Checking participant entry: " + values + " for meeting " + meetingEntry.getObjectEntryId());
                        return entryMeetingId == meetingEntry.getObjectEntryId();
                    })
                    .collect(Collectors.toList());

            Long[] participantIds = participantEntries.stream()
                    .map(entry -> GetterUtil.getLong(entry.getValues().get("participantsID")))
                    .toArray(Long[]::new);

            _log.debug("Participant IDs: " + Arrays.toString(participantIds));
            return participantIds;
        }
        catch (Exception e) {
            _log.error("Error getting participant IDs for meeting " + meetingEntry.getObjectEntryId(), e);
            return new Long[0];
        }
    }

    //for topic response fetch attachments
    public TopicAttachment[] getTopicAttachments(long topicId) {
        try {
            Object attachmentsResult = _attachmentService.getAttachments(topicId);
            List<ObjectEntry> attachmentEntries = new ArrayList<>();

            if (attachmentsResult instanceof List<?>) {
                List<?> resultList = (List<?>) attachmentsResult;
                for (Object obj : resultList) {
                    if (obj instanceof ObjectEntry) {
                        attachmentEntries.add((ObjectEntry) obj);
                    }
                }
            }

            return attachmentEntries.stream()
                    .map(entry -> {
                        TopicAttachment attachment = new TopicAttachment();
                        Map<String, Serializable> values = entry.getValues();
                        attachment.setAttachmentId(entry.getObjectEntryId());
                        attachment.setFileName((String) values.get("fileName"));
                        attachment.setFileBase64((String) values.get("fileBase64"));
                        attachment.setMimeType((String) values.get("mimeType"));
                        return attachment;
                    })
                    .toArray(TopicAttachment[]::new);
        } catch (Exception e) {
            _log.error("Error getting attachments for topic: " + topicId, e);
            return new TopicAttachment[0];
        }
    }

    private void enrichTopicWithMeetingDetails(Topic topic, ObjectEntry topicEntry) {
        try {
            // Get related meetings using the relationship
            List<ObjectEntry> relatedMeetings = ObjectEntryLocalServiceUtil.getManyToManyObjectEntries(
                    topicEntry.getGroupId(),
                    _objectDataHelper.getMeetingTopicRelationshipId(TopicConstants.TOPIC_OBJECT_KEY),
                    topicEntry.getObjectEntryId(),
                    true,
                    true,
                    null,
                    QueryUtil.ALL_POS,
                    QueryUtil.ALL_POS
            );

            _log.info("All meetings found for topic " + topic.getId() + ": " + relatedMeetings.size());

            // Filter out null entries and get unique meetings
            Set<Long> uniqueMeetingIds = new HashSet<>();
            List<Date> meetingDatesList = new ArrayList<>();

            for (ObjectEntry meetingEntry : relatedMeetings) {
                _log.info("meeting entry "+meetingEntry.getObjectEntryId());
                _log.info("meetingDates "+meetingEntry.getValues().get("meetingDateTime"));
                if (meetingEntry != null && uniqueMeetingIds.add(meetingEntry.getObjectEntryId())) {
                    // Only process if this is a new unique meeting
                    Map<String, Serializable> values = meetingEntry.getValues();
                    Object dateObj = values.get("meetingDateTime");
                    if (dateObj instanceof Date) {
                        meetingDatesList.add((Date) dateObj);
                    }
                    _log.info("Added meeting " + meetingEntry.getObjectEntryId() + " to topic " + topic.getId());
                }
            }

            // Set the values
            topic.setNumberOfMeetings(uniqueMeetingIds.size());
            topic.setMeetingDates(meetingDatesList.toArray(new Date[0]));

            _log.info("Topic ID: " + topic.getId() +
                    " has " + uniqueMeetingIds.size() + " unique meetings and " +
                    meetingDatesList.size() + " meeting dates");

        } catch (Exception e) {
            _log.error("Error enriching topic with meeting details for topic ID: " + topic.getId(), e);
            topic.setNumberOfMeetings(0);
            topic.setMeetingDates(new Date[0]);
        }
    }
}