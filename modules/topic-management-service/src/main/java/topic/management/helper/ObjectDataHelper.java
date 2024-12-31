package topic.management.helper;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectRelationshipLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.ncema.dto.v1_0.Meeting;
import com.ncema.dto.v1_0.Topic;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import topic.management.constants.TopicConstants;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Component(service = ObjectDataHelper.class)
public class ObjectDataHelper {

    @Reference
    private ObjectDefinitionHelper _objectDefinitionHelper;

    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;

    private static final Log _log = LogFactoryUtil.getLog(ObjectDataHelper.class);

    public Map<String, Serializable> createTopicValuesMap(Topic topicDto) throws PortalException {
        try {
            Map<String, Serializable> values = new HashMap<>();

            values.put("topicTitle", topicDto.getTopicTitle());
            values.put("topicSubtitle", topicDto.getTopicSubtitle());
            values.put("topicDescription", topicDto.getTopicDescription());
            values.put("topicStatus",TopicConstants.TopicStatus.PENDING_REVISION);
            return values;
        } catch (Exception e) {
            throw new PortalException("Error parsing topic data", e);
        }
    }

    public Map<String, Serializable> createMeetingValuesMap(Meeting meetingDto) {
        try {
            Map<String, Serializable> values = new HashMap<>();

            // Directly use the meetingDto to get values
            values.put("meetingTitle", meetingDto.getMeetingTitle());
            values.put("meetingNumber", meetingDto.getMeetingNumber());
            values.put("meetingDateTime", meetingDto.getMeetingDateTime());
            values.put("meetingLocation", meetingDto.getMeetingLocation());
            values.put("meetingLink", meetingDto.getMeetingLink());

            return values;
        } catch (Exception e) {
            _log.error("Error creating meeting values map", e);
            throw new RuntimeException("Error processing meeting data", e);
        }
    }


    public void deleteParticipants(long meetingId) throws PortalException {
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        ObjectDefinition participantDefinition = _objectDefinitionHelper.getObjectDefinition(
                serviceContext.getCompanyId(), TopicConstants.PARTICIPANT_OBJECT_KEY);

        List<ObjectEntry> participantEntries = _objectEntryLocalService.getObjectEntries(
                        serviceContext.getScopeGroupId(),
                        participantDefinition.getObjectDefinitionId(),
                        QueryUtil.ALL_POS,
                        QueryUtil.ALL_POS
                ).stream()
                .filter(entry -> GetterUtil.getLong(entry.getValues().get("meetingID")) == meetingId)
                .collect(Collectors.toList());

        for (ObjectEntry participantEntry : participantEntries) {
            _objectEntryLocalService.deleteObjectEntry(participantEntry.getObjectEntryId());
        }
    }

    //to add participants in meeting
    public void addParticipants(Meeting meeting, long meetingId, long userId, ServiceContext serviceContext) {
        if (meeting.getParticipants().length > 0 || meeting.getParticipants() != null) {
            ObjectDefinition participantDefinition = _objectDefinitionHelper.getObjectDefinition(
                    serviceContext.getCompanyId(), TopicConstants.PARTICIPANT_OBJECT_KEY);
            for(long participantId : meeting.getParticipants()) {
                try {
                    Map<String, Serializable> participantValues = new HashMap<>();
                    participantValues.put("participantsID", participantId);
                    participantValues.put("meetingID", meetingId);

                    ObjectEntry participantEntry = _objectEntryLocalService.addObjectEntry(
                            userId,
                            serviceContext.getScopeGroupId(),
                            participantDefinition.getObjectDefinitionId(),
                            participantValues,
                            serviceContext
                    );
                    _log.debug("Created participant entry: " + participantEntry.getObjectEntryId());
                } catch (Exception e) {
                    _log.error("Error creating participant entry");
                }
            }
        }
    }

    //to fetch meeting-topic relationshipId
    public long getMeetingTopicRelationshipId(String objectKey) throws PortalException {
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        ObjectDefinition objectDefinition = _objectDefinitionHelper.getObjectDefinition(serviceContext.getCompanyId(),objectKey);
        ObjectRelationship relationship = ObjectRelationshipLocalServiceUtil.getObjectRelationship(objectDefinition.getObjectDefinitionId(),TopicConstants.MEETING_TOPIC_RELATIONSHIP_KEY);
        return relationship.getObjectRelationshipId();
    }
}
