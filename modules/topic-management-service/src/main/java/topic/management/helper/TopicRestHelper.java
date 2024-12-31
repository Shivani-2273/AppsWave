//package topic.management.helper;
//
//import com.liferay.object.model.ObjectDefinition;
//import com.liferay.object.rest.dto.v1_0.ObjectEntry;
//import com.liferay.object.rest.manager.v1_0.ObjectEntryManager;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.model.User;
//import com.liferay.portal.kernel.search.Sort;
//import com.liferay.portal.kernel.service.ServiceContext;
//import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
//import com.liferay.portal.kernel.service.UserLocalServiceUtil;
//import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
//import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
//import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
//import com.liferay.portal.vulcan.pagination.Page;
//import com.liferay.portal.vulcan.pagination.Pagination;
//import com.ncema.dto.v1_0.Topic;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Reference;
//import topic.management.constants.TopicConstants;
//
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.UriInfo;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Component(immediate = true, service = TopicRestHelper.class)
//public class TopicRestHelper {
//
//    private static final Log _log = LogFactoryUtil.getLog(TopicRestHelper.class);
//
//    public Page<Topic> getTopicsWithActions(
//            long companyId, long userId, String search, String filter,
//            Pagination pagination, Sort[] sorts) {
//        _log.info("getTopicsWithAction methods");
//        try {
//            ObjectDefinition objectDefinition = _objectDefinitionHelper.getObjectDefinition(
//                    companyId, TopicConstants.TOPIC_OBJECT_KEY);
//
//            _log.info("getTopicsWithActions objectDefinition: " + objectDefinition.getObjectDefinitionId());
//
//            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
//            _log.info("getTopicsWithActions serviceContext: " + serviceContext);;
//            User user = UserLocalServiceUtil.getUser(userId);
//_log.info("user: " + user);
//            DTOConverterContext dtoConverterContext = new DefaultDTOConverterContext(
//                    _dtoConverterRegistry,
//                    null,
//                    user.getLocale(),
//                    _uriInfo,
//                    user
//            );
//
//            String scopeKey = String.valueOf(serviceContext.getScopeGroupId());
//
//            Page<ObjectEntry> objectEntriesPage = _objectEntryManager.getObjectEntries(
//                    companyId,
//                    objectDefinition,
//                    scopeKey,
//                    null,
//                    dtoConverterContext,
//                    filter,
//                    pagination,
//                    search,
//                    sorts
//            );
//
//            // Convert to Topic and store actions in request attribute
//            List<Topic> topics = objectEntriesPage.getItems().stream()
//                    .map(objectEntry -> {
//                        try {
//                            Topic topic = _objectConverter.convertToTopic((com.liferay.object.model.ObjectEntry) objectEntry);
//
//                            // Store actions in request attribute using topic ID as key
//                            String actionsKey = "topic_actions_" + topic.getId();
//                            serviceContext.getRequest().setAttribute(
//                                    actionsKey,
//                                    new ArrayList<>(objectEntry.getActions().keySet())
//                            );
//
//                            return topic;
//                        } catch (Exception e) {
//                            _log.error("Error converting ObjectEntry to Topic: ", e);
//                            return null;
//                        }
//                    })
//                    .filter(Objects::nonNull)
//                    .collect(Collectors.toList());
//
//            return Page.of(topics, pagination, (int) objectEntriesPage.getTotalCount());
//        } catch (Exception e) {
//            _log.error("Error in getTopicsWithActions: ", e);
//            throw new RuntimeException("Failed to get topics with actions", e);
//        }
//    }
//
//    // Helper method to get actions for a topic
//    public List<String> getTopicActions(long topicId) {
//        try {
//            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
//            String actionsKey = "topic_actions_" + topicId;
//            @SuppressWarnings("unchecked")
//            List<String> actions = (List<String>) serviceContext.getRequest().getAttribute(actionsKey);
//            return actions != null ? actions : new ArrayList<>();
//        } catch (Exception e) {
//            _log.error("Error getting actions for topic: " + topicId, e);
//            return new ArrayList<>();
//        }
//    }
//
//    @Reference
//    private ObjectEntryManager _objectEntryManager;
//
//    @Reference
//    private DTOConverterRegistry _dtoConverterRegistry;
//
//
//    @Reference
//    private ObjectDefinitionHelper _objectDefinitionHelper;
//
//    @Reference
//    private ObjectConverter _objectConverter;
//
//    @Context
//    private UriInfo _uriInfo;
//}
