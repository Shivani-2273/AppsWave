package com.ncema.topic.impl.workflow;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;
import java.io.Serializable;
import java.util.Map;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import topic.management.constants.TopicConstants;
import topic.management.service.TopicService;

@Component(immediate = true, service = ActionExecutor.class)
public class TopicActionExecutor implements ActionExecutor {

	@Override
	public void execute(
			KaleoAction kaleoAction, ExecutionContext executionContext)
			throws ActionExecutorException {

		try {
			Map<String, Serializable> workflowContext = executionContext.getWorkflowContext();
			String transitionName = (String) workflowContext.get("transitionName");


			switch(transitionName) {
					case "delete-topic":
					case "reject-topic":
					case "reject-committee":
						updateTopicStatus(workflowContext, TopicConstants.TopicStatus.REJECTED, WorkflowConstants.STATUS_DENIED);
						break;

					case "approve-topic":
						updateTopicStatus(workflowContext, TopicConstants.TopicStatus.APPROVED, WorkflowConstants.STATUS_APPROVED);
						break;

					case "review-topic":
						updateTopicStatus(workflowContext, TopicConstants.TopicStatus.REVIEWED, WorkflowConstants.STATUS_PENDING);
						break;
			}
		}
		catch (WorkflowException workflowException) {
			_log.error(workflowException, workflowException);

			throw new ActionExecutorException(workflowException);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void updateTopicStatus(Map<String, Serializable> workflowContext, TopicConstants.TopicStatus status, int workflowstatus) throws Exception {
		String objectIdStr = (String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK);
		long objectId = Long.parseLong(objectIdStr);
		String userIdStr = (String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID);
		long userId = Long.parseLong(userIdStr);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(Long.parseLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID)));
		serviceContext.setScopeGroupId(Long.parseLong((String) workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID)));

		_topicService.updateTopicStatus(userId, objectId, status, serviceContext);
		WorkflowStatusManagerUtil.updateStatus(workflowstatus, workflowContext);

	}

	@Override
	public String getActionExecutorKey() {
		return "java";
	}
	private static final Log _log = LogFactoryUtil.getLog(TopicActionExecutor.class);

	@Reference
	TopicService _topicService;

}