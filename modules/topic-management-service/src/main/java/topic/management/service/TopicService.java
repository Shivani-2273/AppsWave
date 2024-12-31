package topic.management.service;

import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.ncema.dto.v1_0.Topic;
import com.ncema.dto.v1_0.WorkflowActivity;
import topic.management.constants.TopicConstants;

import java.util.List;


public interface TopicService {

    Object getTopicById(long topicId) throws PortalException;

    Object createTopic(long userId, Topic topic);

    Object updateTopic(long userId, long topicId, Topic topic) throws PortalException;

    Object deleteTopic(long topicId);

    void updateTopicStatus(long userId, long topicId, TopicConstants.TopicStatus topicStatus, ServiceContext serviceContext);

    //Page<Topic> getTopics(long companyId, long userId, String search, String filter, Pagination pagination, Sort[] sorts);
    Page<Topic> getTopics(long companyId, String search, String filter, Pagination pagination, Sort[] sorts);

    Page<Topic> getTopicsByStatus(String status);

    List<WorkflowActivity> getTopicWorkflowLogs(long topicId);
}