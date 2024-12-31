package topic.management.service.impl;

import com.liferay.object.exception.NoSuchObjectEntryException;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.ncema.dto.v1_0.AttachmentUpdate;
import com.ncema.dto.v1_0.TopicAttachment;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import topic.management.constants.TopicConstants;
import com.liferay.object.service.ObjectEntryLocalService;
import topic.management.helper.ObjectDefinitionHelper;
import topic.management.helper.ResponseUtil;
import topic.management.service.AttachmentService;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        service = AttachmentService.class
)
public class AttachmentServiceImpl implements AttachmentService {

    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;
    

    @Reference
    private ObjectDefinitionHelper _objectDefinitionHelper;

    @Reference
    private ResponseUtil _responseUtil;

    @Override
    public Object addAttachment(long userId, long topicId, String fileName, String fileBase64, String mimeType) {
        try{
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

            Map<String, Serializable> attachmentValues = new HashMap<>();
            attachmentValues.put("topicId", topicId);
            attachmentValues.put("fileName", fileName);
            attachmentValues.put("fileBase64", fileBase64);
            attachmentValues.put("mimeType", mimeType);

            ObjectDefinition objectDefinition = _objectDefinitionHelper.getObjectDefinition(
                    serviceContext.getCompanyId(), TopicConstants.ATTACHMENT_OBJECT_KEY);

            return  _objectEntryLocalService.addObjectEntry(
                    userId,
                    serviceContext.getScopeGroupId(),
                    objectDefinition.getObjectDefinitionId(),
                    attachmentValues,
                    serviceContext
            );
        }catch (Exception e){
            return _responseUtil.response("Error adding attachment", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object getAttachments(long topicId){
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

            // Get object definition
            ObjectDefinition objectDefinition = _objectDefinitionHelper.getObjectDefinition(
                    serviceContext.getCompanyId(),TopicConstants.ATTACHMENT_OBJECT_KEY
            );

            // Get all object entries
            List<ObjectEntry> objectEntries = _objectEntryLocalService.getObjectEntries(
                    serviceContext.getScopeGroupId(),
                    objectDefinition.getObjectDefinitionId(),
                    QueryUtil.ALL_POS,QueryUtil.ALL_POS);

            // Filter and map entries for topicId
            return objectEntries.stream()
                    .filter(entry -> topicId == (Long) entry.getValues().get("topicId"))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            return _responseUtil.response("Error retrieving attachments", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public Object deleteAttachment(long attachmentId) {
        try {
            ObjectEntry attachmentEntry = _objectEntryLocalService.getObjectEntry(attachmentId);
            if(attachmentEntry == null){
                return _responseUtil.response("Attachment not found", Response.Status.NOT_FOUND);
            }
            _objectEntryLocalService.deleteObjectEntry(attachmentId);
            return _responseUtil.response("Attachment deleted successfully", Response.Status.NO_CONTENT);
        } catch (NoSuchObjectEntryException e) {
            return _responseUtil.response("Attachment not found with Id : " + attachmentId, Response.Status.NOT_FOUND);
        } catch (Exception e) {
            return _responseUtil.response("Error deleting attachment", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteTopicAttachments(long topicId) {

            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

            // Get object definition
            ObjectDefinition objectDefinition = _objectDefinitionHelper.getObjectDefinition(
                    serviceContext.getCompanyId(),
                    TopicConstants.ATTACHMENT_OBJECT_KEY
            );

            // Get all attachments for the topic
            List<ObjectEntry> objectEntries = _objectEntryLocalService.getObjectEntries(
                    serviceContext.getScopeGroupId(),
                    objectDefinition.getObjectDefinitionId(),
                    QueryUtil.ALL_POS,
                    QueryUtil.ALL_POS
            );

            // Filter attachments by topicId and delete them
            objectEntries.stream()
                    .filter(entry -> topicId == (Long) entry.getValues().get("topicId"))
                    .forEach(entry -> {
                        try {
                            _objectEntryLocalService.deleteObjectEntry(entry.getObjectEntryId());
                        } catch (PortalException e) {
                            throw new RuntimeException(e);
                        }

                    });

    }

    @Override
    public Object updateAttachment(long userId, long attachmentId, AttachmentUpdate attachmentUpdate) {
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            ObjectEntry existingAttachment = _objectEntryLocalService.getObjectEntry(attachmentId);

            Map<String, Serializable> values = existingAttachment.getValues();
            values.put("fileName", attachmentUpdate.getFileName());
            values.put("fileBase64", attachmentUpdate.getFileBase64());
            values.put("mimeType", attachmentUpdate.getMimeType());

            ObjectEntry updatedEntry = _objectEntryLocalService.updateObjectEntry(
                    userId,
                    attachmentId,
                    values,
                    serviceContext
            );
            // Create TopicAttachment instance directly
            TopicAttachment response = new TopicAttachment();
            response.setAttachmentId(updatedEntry.getObjectEntryId());
            response.setFileName((String) values.get("fileName"));
            response.setFileBase64((String) values.get("fileBase64"));
            response.setMimeType((String) values.get("mimeType"));

            return response;
        }catch (NoSuchObjectEntryException e) {
            return _responseUtil.response("Attachment not found with Id : " + attachmentId, Response.Status.NOT_FOUND);
        } catch (Exception e) {
            return _responseUtil.response("Error updating attachment", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
