package topic.management.service.impl;

import  com.liferay.object.model.ObjectEntry;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.object.exception.NoSuchObjectEntryException;
import com.liferay.object.exception.ObjectEntryValuesException;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;
import com.ncema.dto.v1_0.Topic;
import com.ncema.dto.v1_0.TopicAttachment;
import com.ncema.dto.v1_0.WorkflowActivity;
import org.osgi.service.component.annotations.*;
import topic.management.constants.TopicConstants;
import topic.management.helper.*;
import topic.management.service.AttachmentService;
import topic.management.service.TopicService;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        service = TopicService.class
)
public class TopicServiceImpl implements TopicService {

    private static final Log _log = LogFactoryUtil.getLog(TopicServiceImpl.class);

    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;

    @Reference
    private ResponseUtil _responseUtil;

    @Reference
    ObjectConverter _objectConverter;

    @Reference
    private ObjectDataHelper _objectDataHelper;

    @Reference
    private ObjectDefinitionHelper _objectDefinitionHelper;

    @Reference
    private AttachmentService _attachmentService;

    // Add required services as Reference
    @Reference
    private WorkflowTaskManager _workflowTaskManager;

    @Reference
    private UserLocalService _userLocalService;

    @Override
    public Page<Topic> getTopics(long companyId, String search, String filter,
                                 Pagination pagination, Sort[] sorts){
        try {
            ObjectDefinition objectDefinition = _objectDefinitionHelper.getObjectDefinition(
                    companyId, TopicConstants.TOPIC_OBJECT_KEY);

            String[] selectedFields = {
                    "topicTitle",
                    "topicSubtitle",
                    "topicDescription",
                    "topicStatus",
                    "documents"
            };

            // Get values list
            List<Map<String, Serializable>> valuesList = _objectEntryLocalService.getValuesList(
                    ServiceContextThreadLocal.getServiceContext().getScopeGroupId(),
                    companyId,
                    objectDefinition.getUserId(),
                    objectDefinition.getObjectDefinitionId(),
                    selectedFields,
                    null,
                    search,
                    pagination.getStartPosition(),
                    pagination.getEndPosition(),
                    sorts
            );

            List<Topic> topics = valuesList.stream()
                    .map(values -> {
                        try {
                            ObjectEntry objectEntry = _objectEntryLocalService.getObjectEntry(
                                    (Long) values.get("objectEntryId")
                            );
                            return  _objectConverter.convertToTopic(objectEntry);
                        } catch (Exception e) {
                            _log.error("Error converting values to Topic: ", e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            int totalCount = _objectEntryLocalService.getObjectEntriesCount(
                    ServiceContextThreadLocal.getServiceContext().getScopeGroupId(),
                    objectDefinition.getObjectDefinitionId()
            );

            return Page.of(topics, pagination, totalCount);

        } catch (Exception e) {
            _log.error("Error in getTopics method: ", e);
        }
        return null;
    }


    @Override
    public Page<Topic> getTopicsByStatus(String status) {
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            ObjectDefinition objectDefinition = _objectDefinitionHelper.getObjectDefinition(
                    serviceContext.getCompanyId(), TopicConstants.TOPIC_OBJECT_KEY);

            // Get all entries for this object definition
            List<ObjectEntry> objectEntries = _objectEntryLocalService.getObjectEntries(
                    serviceContext.getScopeGroupId(),
                    objectDefinition.getObjectDefinitionId(),
                    QueryUtil.ALL_POS,
                    QueryUtil.ALL_POS
            );

            // Validate and convert the status
            TopicConstants.TopicStatus topicStatus;
            try {
                topicStatus = TopicConstants.TopicStatus.fromKey(status);
            } catch (IllegalArgumentException e) {
                _log.error("Invalid topic status: " + status);
                return Page.of(Collections.emptyList());
            }

            // Filter and convert to topics
            List<Topic> filteredTopics = objectEntries.stream()
                    .filter(entry -> {
                        Map<String, Serializable> values = entry.getValues();
                        return topicStatus.toString().equals(values.get("topicStatus").toString());
                    })
                    .map(_objectConverter::convertToTopic)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            return Page.of(filteredTopics);

        } catch (Exception e) {
            _log.error("Error in getTopicsByStatus method: ", e);
            return Page.of(Collections.emptyList());
        }
    }

    @Override
    public List<WorkflowActivity> getTopicWorkflowLogs(long topicId) {
        try {
            List<KaleoInstance> kaleoInstances = KaleoInstanceLocalServiceUtil.getKaleoInstances(
                    QueryUtil.ALL_POS,
                    QueryUtil.ALL_POS);

            // Find instance for our topic
            Optional<KaleoInstance> topicInstance = kaleoInstances.stream()
                    .filter(instance -> instance.getClassPK() == topicId)
                    .findFirst();

            if (topicInstance.isEmpty()) {
                _log.warn("No workflow instance found for topic: " + topicId);
                return Collections.emptyList();
            }

            // Get logs for this instance
            List<KaleoLog> kaleoLogs = KaleoLogLocalServiceUtil.getKaleoInstanceKaleoLogs(
                    topicInstance.get().getCompanyId(),
                    topicInstance.get().getKaleoInstanceId(),
                    new ArrayList<>(),
                    QueryUtil.ALL_POS,
                    QueryUtil.ALL_POS,
                    null
            );

            // Convert and return logs
            return kaleoLogs.stream()
                    .map(this::convertToWorkflowActivity)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            _log.error("Error fetching workflow logs for topic: " + topicId, e);
            return Collections.emptyList();
        }
    }

    private WorkflowActivity convertToWorkflowActivity(KaleoLog log) {
        WorkflowActivity activity = new WorkflowActivity();
        try {
            activity.setLogId(log.getKaleoLogId());
            activity.setUserId(log.getUserId());

            User user = _userLocalService.fetchUser(log.getUserId());
            activity.setUserName(user != null ? user.getFullName() : "Unknown User");

            activity.setCreateDate(log.getCreateDate());
            activity.setActivityType(log.getType());
            activity.setComment(log.getComment());
            activity.setPreviousNode(log.getPreviousKaleoNodeName());
            activity.setCurrentNode(log.getKaleoNodeName());
            activity.setTaskId(log.getKaleoTaskInstanceTokenId());
            activity.setIsTerminal(log.isTerminalKaleoNode());

        } catch (Exception e) {
            _log.error("Error converting workflow log to activity", e);
        }
        return activity;
    }


    @Override
    public Object getTopicById(long topicId) {
        try {
            ObjectEntry objectEntry = _objectEntryLocalService.getObjectEntry(topicId);
            return _objectConverter.convertToTopic(objectEntry);
        }
        catch (NoSuchObjectEntryException e) {
            return _responseUtil.response("Topic Not Found with Id : " + topicId, Response.Status.NOT_FOUND);
        }
        catch (Exception e) {
            return _responseUtil.response("Error retrieving topic",Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public Object deleteTopic( long topicId) {
        try {
            ObjectEntry objectEntry = _objectEntryLocalService.getObjectEntry(topicId);
            if(objectEntry.getValues().get("topicStatus").toString().equals(TopicConstants.TopicStatus.PROCESSED.toString())){
                return _responseUtil.response("Topic is in a 'Processed' state and cannot be removed.", Response.Status.BAD_REQUEST);
            }
            _attachmentService.deleteTopicAttachments(topicId);
             _objectEntryLocalService.deleteObjectEntry(objectEntry.getObjectEntryId());

            return _responseUtil.response("Topic and related attachments deleted successfully", Response.Status.NO_CONTENT);
        }
        catch (NoSuchObjectEntryException e) {
            return _responseUtil.response("Topic not found with Id : " + topicId, Response.Status.NOT_FOUND);
        }
        catch (Exception e) {
            _log.error("Error deleting topic: ", e);
            return _responseUtil.response("Error deleting topic", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateTopicStatus(long userId, long topicId, TopicConstants.TopicStatus topicStatus, ServiceContext serviceContext){
        try {
            ObjectEntry existingEntry = _objectEntryLocalService.getObjectEntry(topicId);
            Map<String, Serializable> values = new HashMap<>(existingEntry.getValues());
            values.put("topicStatus", topicStatus);
            existingEntry.setValues(values);
            ObjectEntry updateEntry = _objectEntryLocalService.updateObjectEntry(
                    userId,
                    existingEntry.getObjectEntryId(),
                    values,
                    serviceContext
            );
            _objectConverter.convertToTopic(updateEntry);
        }catch (NoSuchObjectEntryException e) {
            _responseUtil.response("Topic not found with Id : " + topicId, Response.Status.NOT_FOUND);
        }catch (ObjectEntryValuesException e) {
            _responseUtil.response("Invalid topic status value", Response.Status.BAD_REQUEST);
        }catch (Exception e) {
            _responseUtil.response("Error updating topic status", Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public Object createTopic(long userId, Topic topic)  {
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        ObjectDefinition objectDefinition = _objectDefinitionHelper.getObjectDefinition(serviceContext.getCompanyId(), TopicConstants.TOPIC_OBJECT_KEY);
        try {

            Map<String, Serializable> values = _objectDataHelper.createTopicValuesMap(topic);
            ObjectEntry objectEntry = _objectEntryLocalService.addObjectEntry(
                    userId,
                    serviceContext.getScopeGroupId(),
                    objectDefinition.getObjectDefinitionId(),
                    values,
                    serviceContext
            );
            // Handle attachments if present
            if (topic.getTopicAttachments() != null) {
                for (TopicAttachment attachment : topic.getTopicAttachments()) {
                    _attachmentService.addAttachment(
                            userId,
                            objectEntry.getObjectEntryId(),
                            attachment.getFileName(),
                            attachment.getFileBase64(),
                            attachment.getMimeType()
                    );
                }
            }
            return _objectConverter.convertToTopic(objectEntry);
        }
        catch (Exception e) {
            return _responseUtil.response(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object updateTopic(long userId, long topicId, Topic topic) {
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            ObjectEntry existingEntry = _objectEntryLocalService.getObjectEntry(topicId);

            if (existingEntry == null) {
                return _responseUtil.response("Topic Not Found with Id : " + topicId, Response.Status.NOT_FOUND);
            }
            if(existingEntry.getValues().get("topicStatus").toString().equals(TopicConstants.TopicStatus.REVIEWED.toString())){
                return _responseUtil.response("Topic is Reviewed you can not edit this topic", Response.Status.BAD_REQUEST);
            }
            // Handle attachments first
            if (topic.getTopicAttachments() != null) {

                for (TopicAttachment attachment : topic.getTopicAttachments()) {
                    if (attachment.getAttachmentId() != null) {
                        try {
                            // Check if attachment exists
                            _objectEntryLocalService.getObjectEntry(attachment.getAttachmentId());
                        } catch (NoSuchObjectEntryException e) {
                            return _responseUtil.response(
                                    "Invalid attachment ID: " + attachment.getAttachmentId(),
                                    Response.Status.BAD_REQUEST
                            );
                        }
                    }
                }

                // Get list of attachmentIds that should be preserved
                Set<Long> preserveAttachmentIds = Arrays.stream(topic.getTopicAttachments())
                        .map(TopicAttachment::getAttachmentId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());

                // Get current attachments
                List<ObjectEntry> currentAttachments =
                        (List<ObjectEntry>) _attachmentService.getAttachments(topicId);

                // Delete attachments not in preserveAttachmentIds
                if (currentAttachments != null) {
                    currentAttachments.stream()
                            .map(ObjectEntry::getObjectEntryId)
                            .filter(id -> !preserveAttachmentIds.contains(id))
                            .forEach(id -> _attachmentService.deleteAttachment(id));
                }

                // Add new attachments (ones without attachmentId)
                Arrays.stream(topic.getTopicAttachments())
                        .filter(attachment -> attachment.getAttachmentId() == null)
                        .forEach(attachment -> _attachmentService.addAttachment(
                                userId,
                                topicId,
                                attachment.getFileName(),
                                attachment.getFileBase64(),
                                attachment.getMimeType()
                        ));
            }

            Map<String, Serializable> values = _objectDataHelper.createTopicValuesMap(topic);
            ObjectEntry updatedEntry = _objectEntryLocalService.updateObjectEntry(
                    userId,
                    topicId,
                    values,
                    serviceContext
            );
            return _objectConverter.convertToTopic(updatedEntry);

        } catch (NoSuchObjectEntryException e) {
            return _responseUtil.response("Topic not found with Id : " + topicId, Response.Status.NOT_FOUND );
        } catch (Exception e) {
            return _responseUtil.response(e.getMessage()    , Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}



