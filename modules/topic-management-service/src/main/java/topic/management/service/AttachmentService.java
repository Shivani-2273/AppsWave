package topic.management.service;

import com.ncema.dto.v1_0.AttachmentUpdate;


public interface AttachmentService {

    Object addAttachment(long userId, long topicId, String fileName, String fileBase64, String mimeType);

    Object getAttachments(long topicId);

    Object deleteAttachment(long attachmentId);

    void deleteTopicAttachments(long topicId);

    Object updateAttachment(long userId, long attachmentId, AttachmentUpdate attachmentUpdate);
}
