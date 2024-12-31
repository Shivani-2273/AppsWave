package com.ncema.internal.graphql.servlet.v1_0;

import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import com.ncema.internal.graphql.mutation.v1_0.Mutation;
import com.ncema.internal.graphql.query.v1_0.Query;
import com.ncema.resource.v1_0.MeetingResource;
import com.ncema.resource.v1_0.TopicResource;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author root316
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setMeetingResourceComponentServiceObjects(
			_meetingResourceComponentServiceObjects);
		Mutation.setTopicResourceComponentServiceObjects(
			_topicResourceComponentServiceObjects);

		Query.setMeetingResourceComponentServiceObjects(
			_meetingResourceComponentServiceObjects);
		Query.setTopicResourceComponentServiceObjects(
			_topicResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/topic-rest-builder-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<MeetingResource>
		_meetingResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TopicResource>
		_topicResourceComponentServiceObjects;

}