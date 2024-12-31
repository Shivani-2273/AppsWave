package com.ncema.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import com.ncema.dto.v1_0.Meeting;
import com.ncema.dto.v1_0.Topic;
import com.ncema.resource.v1_0.MeetingResource;
import com.ncema.resource.v1_0.TopicResource;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author root316
 * @generated
 */
@Generated("")
public class Mutation {

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

	@GraphQLField
	public Response createMeeting(@GraphQLName("meeting") Meeting meeting)
		throws Exception {

		return _applyComponentServiceObjects(
			_meetingResourceComponentServiceObjects,
			this::_populateResourceContext,
			meetingResource -> meetingResource.createMeeting(meeting));
	}

	@GraphQLField
	public Response deleteMeeting(@GraphQLName("meetingId") Long meetingId)
		throws Exception {

		return _applyComponentServiceObjects(
			_meetingResourceComponentServiceObjects,
			this::_populateResourceContext,
			meetingResource -> meetingResource.deleteMeeting(meetingId));
	}

	@GraphQLField
	public Response deleteMeetingBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_meetingResourceComponentServiceObjects,
			this::_populateResourceContext,
			meetingResource -> meetingResource.deleteMeetingBatch(
				callbackURL, object));
	}

	@GraphQLField
	public Response updateMeeting(
			@GraphQLName("meetingId") Long meetingId,
			@GraphQLName("meeting") Meeting meeting)
		throws Exception {

		return _applyComponentServiceObjects(
			_meetingResourceComponentServiceObjects,
			this::_populateResourceContext,
			meetingResource -> meetingResource.updateMeeting(
				meetingId, meeting));
	}

	@GraphQLField
	public Response createTopic(@GraphQLName("topic") Topic topic)
		throws Exception {

		return _applyComponentServiceObjects(
			_topicResourceComponentServiceObjects,
			this::_populateResourceContext,
			topicResource -> topicResource.createTopic(topic));
	}

	@GraphQLField(description = "ID of the topic")
	public Response deleteTopic(@GraphQLName("topicId") Long topicId)
		throws Exception {

		return _applyComponentServiceObjects(
			_topicResourceComponentServiceObjects,
			this::_populateResourceContext,
			topicResource -> topicResource.deleteTopic(topicId));
	}

	@GraphQLField
	public Response deleteTopicBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_topicResourceComponentServiceObjects,
			this::_populateResourceContext,
			topicResource -> topicResource.deleteTopicBatch(
				callbackURL, object));
	}

	@GraphQLField
	public Response updateTopic(
			@GraphQLName("topicId") Long topicId,
			@GraphQLName("topic") Topic topic)
		throws Exception {

		return _applyComponentServiceObjects(
			_topicResourceComponentServiceObjects,
			this::_populateResourceContext,
			topicResource -> topicResource.updateTopic(topicId, topic));
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
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
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}