package com.ncema.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import com.ncema.dto.v1_0.Meeting;
import com.ncema.dto.v1_0.Topic;
import com.ncema.resource.v1_0.MeetingResource;
import com.ncema.resource.v1_0.TopicResource;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author root316
 * @generated
 */
@Generated("")
public class Query {

	public static void setMeetingResourceComponentServiceObjects(
		ComponentServiceObjects<MeetingResource>
			meetingResourceComponentServiceObjects) {

		_meetingResourceComponentServiceObjects =
			meetingResourceComponentServiceObjects;
	}

	public static void setTopicResourceComponentServiceObjects(
		ComponentServiceObjects<TopicResource>
			topicResourceComponentServiceObjects) {

		_topicResourceComponentServiceObjects =
			topicResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {meetings(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public MeetingPage meetings(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_meetingResourceComponentServiceObjects,
			this::_populateResourceContext,
			meetingResource -> new MeetingPage(
				meetingResource.getMeetings(
					search,
					_filterBiFunction.apply(meetingResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(meetingResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {meetingById(meetingId: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Object meetingById(@GraphQLName("meetingId") Long meetingId)
		throws Exception {

		return _applyComponentServiceObjects(
			_meetingResourceComponentServiceObjects,
			this::_populateResourceContext,
			meetingResource -> meetingResource.getMeetingById(meetingId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {meetingParticipants(meetingId: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public MeetingPage meetingParticipants(
			@GraphQLName("meetingId") Long meetingId)
		throws Exception {

		return _applyComponentServiceObjects(
			_meetingResourceComponentServiceObjects,
			this::_populateResourceContext,
			meetingResource -> new MeetingPage(
				meetingResource.getMeetingParticipants(meetingId)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {topics(filterStr: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TopicPage topics(
			@GraphQLName("search") String search,
			@GraphQLName("filterStr") String filterStr,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_topicResourceComponentServiceObjects,
			this::_populateResourceContext,
			topicResource -> new TopicPage(
				topicResource.getTopics(
					search, filterStr, Pagination.of(page, pageSize),
					_sortsBiFunction.apply(topicResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {topicById(topicId: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "ID of the topic")
	public Object topicById(@GraphQLName("topicId") Long topicId)
		throws Exception {

		return _applyComponentServiceObjects(
			_topicResourceComponentServiceObjects,
			this::_populateResourceContext,
			topicResource -> topicResource.getTopicById(topicId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {topicsByStatus(topicStatus: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TopicPage topicsByStatus(
			@GraphQLName("topicStatus") String topicStatus)
		throws Exception {

		return _applyComponentServiceObjects(
			_topicResourceComponentServiceObjects,
			this::_populateResourceContext,
			topicResource -> new TopicPage(
				topicResource.getTopicsByStatus(topicStatus)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {topicWorkflowLogs(topicId: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TopicPage topicWorkflowLogs(@GraphQLName("topicId") Long topicId)
		throws Exception {

		return _applyComponentServiceObjects(
			_topicResourceComponentServiceObjects,
			this::_populateResourceContext,
			topicResource -> new TopicPage(
				topicResource.getTopicWorkflowLogs(topicId)));
	}

	@GraphQLName("MeetingPage")
	public class MeetingPage {

		public MeetingPage(Page meetingPage) {
			actions = meetingPage.getActions();

			items = meetingPage.getItems();
			lastPage = meetingPage.getLastPage();
			page = meetingPage.getPage();
			pageSize = meetingPage.getPageSize();
			totalCount = meetingPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Meeting> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("TopicPage")
	public class TopicPage {

		public TopicPage(Page topicPage) {
			actions = topicPage.getActions();

			items = topicPage.getItems();
			lastPage = topicPage.getLastPage();
			page = topicPage.getPage();
			pageSize = topicPage.getPageSize();
			totalCount = topicPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Topic> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(MeetingResource meetingResource)
		throws Exception {

		meetingResource.setContextAcceptLanguage(_acceptLanguage);
		meetingResource.setContextCompany(_company);
		meetingResource.setContextHttpServletRequest(_httpServletRequest);
		meetingResource.setContextHttpServletResponse(_httpServletResponse);
		meetingResource.setContextUriInfo(_uriInfo);
		meetingResource.setContextUser(_user);
		meetingResource.setGroupLocalService(_groupLocalService);
		meetingResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(TopicResource topicResource)
		throws Exception {

		topicResource.setContextAcceptLanguage(_acceptLanguage);
		topicResource.setContextCompany(_company);
		topicResource.setContextHttpServletRequest(_httpServletRequest);
		topicResource.setContextHttpServletResponse(_httpServletResponse);
		topicResource.setContextUriInfo(_uriInfo);
		topicResource.setContextUser(_user);
		topicResource.setGroupLocalService(_groupLocalService);
		topicResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<MeetingResource>
		_meetingResourceComponentServiceObjects;
	private static ComponentServiceObjects<TopicResource>
		_topicResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}