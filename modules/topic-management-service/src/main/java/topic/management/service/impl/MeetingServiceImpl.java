package topic.management.service.impl;

import com.liferay.object.exception.NoSuchObjectEntryException;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectRelationshipLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.ncema.dto.v1_0.Meeting;
import com.ncema.dto.v1_0.MeetingResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import topic.management.constants.TopicConstants;
import topic.management.helper.ObjectConverter;
import topic.management.helper.ObjectDataHelper;
import topic.management.helper.ObjectDefinitionHelper;
import topic.management.helper.ResponseUtil;
import topic.management.service.MeetingService;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        service = MeetingService.class
)
public class MeetingServiceImpl implements MeetingService {

    private static final Log _log = LogFactoryUtil.getLog(MeetingServiceImpl.class);

    @Reference
    ObjectDefinitionHelper _objectDefinitionHelper;

    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;

    @Reference
    private ObjectConverter _objectConverter;

    @Reference
    private ObjectDataHelper _objectDataHelper;

    @Reference
    private ResponseUtil _responseUtil;

    @Override
    public Object createMeeting(long userId, Meeting meeting) {

        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

        ObjectDefinition meetingDefinition = _objectDefinitionHelper.getObjectDefinition(serviceContext.getCompanyId(), TopicConstants.MEETING_OBJECT_KEY);
        try {
            Map<String, Serializable> values= _objectDataHelper.createMeetingValuesMap(meeting);

            ObjectEntry meetingEntry = _objectEntryLocalService.addObjectEntry(
                    userId,
                    serviceContext.getScopeGroupId(),
                    meetingDefinition.getObjectDefinitionId(),
                    values,
                    serviceContext
            );
            //add participants
            _objectDataHelper.addParticipants(meeting, meetingEntry.getObjectEntryId(), userId, serviceContext);

            //add topic relationship
            addTopicRelationship(meeting,meetingEntry.getObjectEntryId(),userId,serviceContext);

            return _objectConverter.convertToMeeting(meetingEntry);
        }
        catch (Exception e) {
            return _responseUtil.response("Error creating meeting: ", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Page<MeetingResponse> getMeetings(long companyId, String search, Filter filter, Pagination pagination, Sort[] sorts) {
        try {
            ObjectDefinition meetingDefinition = _objectDefinitionHelper.getObjectDefinition(
                    companyId, TopicConstants.MEETING_OBJECT_KEY);

            if (pagination == null) {
                pagination = Pagination.of(1, 20);
            }

            // Define fields to retrieve
            String[] selectedFields = {"meetingTitle", "meetingNumber", "meetingLocation", "meetingLink", "meetingDateTime", "participants"};
            // Get values list
            List<Map<String, Serializable>> valuesList = _objectEntryLocalService.getValuesList(
                    ServiceContextThreadLocal.getServiceContext().getScopeGroupId(),
                    companyId,
                    meetingDefinition.getUserId(),
                    meetingDefinition.getObjectDefinitionId(),
                    selectedFields,
                    null,
                    search,
                    pagination.getStartPosition(),
                    pagination.getEndPosition(),
                    sorts
            );

            // Convert to Meeting
            List<MeetingResponse> meetings = valuesList.stream()
                    .map(values -> {
                        try {
                            // Get the ObjectEntry using the values
                            ObjectEntry objectEntry = _objectEntryLocalService.getObjectEntry(
                                    (Long) values.get("objectEntryId")
                            );
                            return _objectConverter.convertToMeeting(objectEntry);
                        } catch (Exception e) {
                            _log.error("Error converting values to Topic: ", e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Get total count for pagination
            int totalCount = _objectEntryLocalService.getObjectEntriesCount(
                    ServiceContextThreadLocal.getServiceContext().getScopeGroupId(),
                    meetingDefinition.getObjectDefinitionId()
            );


            return Page.of(meetings, pagination, totalCount);
        } catch (Exception e) {
            _log.error("Error getting meetings: ");

        }
        return null;
    }

    @Override
    public Object getMeetingById(long meetingId) {
        try {
            return _objectConverter.convertToMeeting( _objectEntryLocalService.getObjectEntry(meetingId));
        }  catch (NoSuchObjectEntryException e) {
            return _responseUtil.response("Meeting Not Found with Id : " + meetingId, Response.Status.NOT_FOUND);
        } catch (Exception e) {
            return _responseUtil.response("Error retrieving meeting: "+e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object deleteMeeting(long meetingId){
        try {
            ObjectEntry meetingEntry = _objectEntryLocalService.getObjectEntry(meetingId);
            if (meetingEntry == null) {
                return _responseUtil.response("Meeting Not Found with Id : " + meetingId, Response.Status.NOT_FOUND);
            }
            _objectDataHelper.deleteParticipants(meetingEntry.getObjectEntryId());
            deleteTopicRelationships(meetingEntry.getObjectEntryId());
            _objectEntryLocalService.deleteObjectEntry(meetingId);
            return _responseUtil.response("Meeting deleted successfully", Response.Status.NO_CONTENT);
        } catch (NoSuchObjectEntryException e) {
            return _responseUtil.response("Meeting Not Found with Id : " + meetingId, Response.Status.NOT_FOUND);
        } catch (Exception e) {
            return _responseUtil.response("Error deleting meeting: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Long> getMeetingParticipants(long meetingId){
        try {
            ObjectEntry meetingEntry = _objectEntryLocalService.getObjectEntry(meetingId);
            return Arrays.asList(_objectConverter.getParticipantIds(meetingEntry));
        } catch (Exception e) {
            return Collections.emptyList();         }
    }

    @Override
    public Object updateMeeting(long userId, long meetingId, Meeting meeting) {
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

            // Get existing meeting
            ObjectEntry existingEntry = _objectEntryLocalService.getObjectEntry(meetingId);
            if (existingEntry == null) {
                return _responseUtil.response("Meeting Not Found with Id : " + meetingId, Response.Status.NOT_FOUND);
            }

            // Update meeting entry
            Map<String, Serializable> values = _objectDataHelper.createMeetingValuesMap(meeting);
            ObjectEntry updatedEntry = _objectEntryLocalService.updateObjectEntry(
                    userId,
                    meetingId,
                    values,
                    serviceContext
            );

            // Delete existing participants
            _objectDataHelper.deleteParticipants(meetingId);

            // Add new participants
            _objectDataHelper.addParticipants(meeting, meetingId, userId, serviceContext);

            // Update topic relationships
            deleteTopicRelationships(meetingId);
            addTopicRelationship(meeting, meetingId, userId, serviceContext);


            return _objectConverter.convertToMeeting(updatedEntry);
        }catch (NoSuchObjectEntryException e){
            return _responseUtil.response("Meeting Not Found with Id : " + meetingId, Response.Status.NOT_FOUND);
        } catch (Exception e) {
           return _responseUtil.response("Error updating meeting: "+e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }



    private void addTopicRelationship(Meeting meeting, long meetingId, long userId, ServiceContext serviceContext)
            throws PortalException {
        _log.info("addTopicRelationship");
        if (meeting.getTopics() != null) {
            _log.info("not null");
            for (Long topicId : meeting.getTopics()) {
                _log.info("topicId : " + topicId);
                try {
                    ObjectEntry topicEntry = _objectEntryLocalService.getObjectEntry(topicId);
                    //if topic is approved then allow to add in meeting
//                    if(topicEntry.getValues().get("topicStatus").toString().equals(TopicConstants.TopicStatus.REVIEWED.toString()))){
//
//                    }

                    _log.info("topicEntry : " + topicEntry);
                    if(topicEntry == null) {
                        _log.error("Topic Not Found with Id : " + topicId);
                        continue;
                    }

                    ObjectRelationshipLocalServiceUtil.addObjectRelationshipMappingTableValues(
                            userId,
                            _objectDataHelper.getMeetingTopicRelationshipId(TopicConstants.MEETING_OBJECT_KEY),
                            meetingId,
                            topicId,
                            serviceContext
                    );
                } catch (NoSuchObjectEntryException e) {
                    _log.error("Topic not found with ID: " + topicId);
                }
            }
        }
    }

    private void deleteTopicRelationships(long meetingId) throws PortalException {
        long relationshipId = _objectDataHelper.getMeetingTopicRelationshipId(TopicConstants.MEETING_OBJECT_KEY);
        ObjectRelationshipLocalServiceUtil.deleteObjectRelationshipMappingTableValues(
                relationshipId,
                meetingId
        );
    }
}
