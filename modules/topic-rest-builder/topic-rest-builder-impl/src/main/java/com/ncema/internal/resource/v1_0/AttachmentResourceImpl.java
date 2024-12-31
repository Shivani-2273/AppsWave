package com.ncema.internal.resource.v1_0;

import com.ncema.dto.v1_0.AttachmentUpdate;
import com.ncema.resource.v1_0.AttachmentResource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import topic.management.service.AttachmentService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author root316
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/attachment.properties",
	scope = ServiceScope.PROTOTYPE, service = AttachmentResource.class
)
public class AttachmentResourceImpl extends BaseAttachmentResourceImpl {

	@Reference
	AttachmentService _attachmentService;

	@Override
	public Response deleteAttachment(Long attachmentId) {
		return Response.ok(_attachmentService.deleteAttachment(attachmentId)).type(MediaType.APPLICATION_JSON).build();
	}

	@Override
	public Response updateAttachment(Long attachmentId, AttachmentUpdate attachmentUpdate) throws Exception {
		return  Response.ok(_attachmentService.updateAttachment(contextUser.getUserId(),attachmentId,attachmentUpdate)).build();
	}

}