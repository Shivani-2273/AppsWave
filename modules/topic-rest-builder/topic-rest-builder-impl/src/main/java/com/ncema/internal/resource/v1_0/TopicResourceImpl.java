package com.ncema.internal.resource.v1_0;

import com.liferay.petra.function.UnsafeBiConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.ncema.dto.v1_0.Topic;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ncema.dto.v1_0.WorkflowActivity;
import com.ncema.resource.v1_0.TopicResource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import topic.management.service.TopicService;
import java.util.*;
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/topic.properties",
	scope = ServiceScope.PROTOTYPE, service = TopicResource.class
)
public class TopicResourceImpl extends BaseTopicResourceImpl {

	@Reference
	TopicService _topicService;

	@Override
	public Page<Topic> getTopics(String search, String filter, Pagination pagination, Sort[] sorts) throws Exception {
		return _topicService.getTopics(contextCompany.getCompanyId(),search, filter, pagination, sorts);
	}

	@Override
	public Object getTopicById(Long topicId) throws Exception {
		return _topicService.getTopicById(topicId);
	}

	@Override
	public Response deleteTopic(Long topicId) {
		return Response.ok(_topicService.deleteTopic(topicId)).type(MediaType.APPLICATION_JSON).build();
	}

	@Override
	public Response createTopic(Topic topic) {
		return Response.ok(_topicService.createTopic(contextUser.getUserId(),topic)).build();
	}

	@Override
	public Response updateTopic(Long topicId, Topic topic) throws Exception {
		return  Response.ok(_topicService.updateTopic(contextUser.getUserId(),topicId,topic)).build();
	}

	@Override
	public Page<Topic> getTopicsByStatus(String topicStatus){
		return _topicService.getTopicsByStatus(topicStatus);
	}

	@Override
	public Page<WorkflowActivity> getTopicWorkflowLogs(Long topicId) throws Exception {
		List<WorkflowActivity> logs = _topicService.getTopicWorkflowLogs(topicId);
		return Page.of(logs);
	}


		@Override
	public void setContextBatchUnsafeBiConsumer(UnsafeBiConsumer<Collection<Topic>, UnsafeFunction<Topic, Topic, Exception>, Exception> unsafeBiConsumer) {
	}

}