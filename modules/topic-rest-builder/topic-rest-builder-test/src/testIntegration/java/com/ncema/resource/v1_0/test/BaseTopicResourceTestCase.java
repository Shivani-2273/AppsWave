package com.ncema.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import com.ncema.client.dto.v1_0.Topic;
import com.ncema.client.http.HttpInvoker;
import com.ncema.client.pagination.Page;
import com.ncema.client.pagination.Pagination;
import com.ncema.client.resource.v1_0.TopicResource;
import com.ncema.client.serdes.v1_0.TopicSerDes;

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author root316
 * @generated
 */
@Generated("")
public abstract class BaseTopicResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_topicResource.setContextCompany(testCompany);

		TopicResource.Builder builder = TopicResource.builder();

		topicResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Topic topic1 = randomTopic();

		String json = objectMapper.writeValueAsString(topic1);

		Topic topic2 = TopicSerDes.toDTO(json);

		Assert.assertTrue(equals(topic1, topic2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Topic topic = randomTopic();

		String json1 = objectMapper.writeValueAsString(topic);
		String json2 = TopicSerDes.toJSON(topic);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Topic topic = randomTopic();

		topic.setTopicDescription(regex);
		topic.setTopicStatus(regex);
		topic.setTopicSubtitle(regex);
		topic.setTopicTitle(regex);

		String json = TopicSerDes.toJSON(topic);

		Assert.assertFalse(json.contains(regex));

		topic = TopicSerDes.toDTO(json);

		Assert.assertEquals(regex, topic.getTopicDescription());
		Assert.assertEquals(regex, topic.getTopicStatus());
		Assert.assertEquals(regex, topic.getTopicSubtitle());
		Assert.assertEquals(regex, topic.getTopicTitle());
	}

	@Test
	public void testGetTopics() throws Exception {
		Page<Topic> page = topicResource.getTopics(
			null, RandomTestUtil.randomString(), Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		Topic topic1 = testGetTopics_addTopic(randomTopic());

		Topic topic2 = testGetTopics_addTopic(randomTopic());

		page = topicResource.getTopics(null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(topic1, (List<Topic>)page.getItems());
		assertContains(topic2, (List<Topic>)page.getItems());
		assertValid(page);

		topicResource.deleteTopic(topic1.getId());

		topicResource.deleteTopic(topic2.getId());
	}

	@Test
	public void testGetTopicsWithPagination() throws Exception {
		Page<Topic> totalPage = topicResource.getTopics(null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		Topic topic1 = testGetTopics_addTopic(randomTopic());

		Topic topic2 = testGetTopics_addTopic(randomTopic());

		Topic topic3 = testGetTopics_addTopic(randomTopic());

		Page<Topic> page1 = topicResource.getTopics(
			null, null, Pagination.of(1, totalCount + 2), null);

		List<Topic> topics1 = (List<Topic>)page1.getItems();

		Assert.assertEquals(topics1.toString(), totalCount + 2, topics1.size());

		Page<Topic> page2 = topicResource.getTopics(
			null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<Topic> topics2 = (List<Topic>)page2.getItems();

		Assert.assertEquals(topics2.toString(), 1, topics2.size());

		Page<Topic> page3 = topicResource.getTopics(
			null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(topic1, (List<Topic>)page3.getItems());
		assertContains(topic2, (List<Topic>)page3.getItems());
		assertContains(topic3, (List<Topic>)page3.getItems());
	}

	@Test
	public void testGetTopicsWithSortDateTime() throws Exception {
		testGetTopicsWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, topic1, topic2) -> {
				BeanUtils.setProperty(
					topic1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetTopicsWithSortInteger() throws Exception {
		testGetTopicsWithSort(
			EntityField.Type.INTEGER,
			(entityField, topic1, topic2) -> {
				BeanUtils.setProperty(topic1, entityField.getName(), 0);
				BeanUtils.setProperty(topic2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetTopicsWithSortString() throws Exception {
		testGetTopicsWithSort(
			EntityField.Type.STRING,
			(entityField, topic1, topic2) -> {
				Class<?> clazz = topic1.getClass();

				String entityFieldName = entityField.getName();

				java.lang.reflect.Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						topic1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						topic2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanUtils.setProperty(
						topic1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanUtils.setProperty(
						topic2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanUtils.setProperty(
						topic1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanUtils.setProperty(
						topic2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetTopicsWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, Topic, Topic, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Topic topic1 = randomTopic();
		Topic topic2 = randomTopic();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, topic1, topic2);
		}

		topic1 = testGetTopics_addTopic(topic1);

		topic2 = testGetTopics_addTopic(topic2);

		for (EntityField entityField : entityFields) {
			Page<Topic> ascPage = topicResource.getTopics(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(topic1, topic2), (List<Topic>)ascPage.getItems());

			Page<Topic> descPage = topicResource.getTopics(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(topic2, topic1),
				(List<Topic>)descPage.getItems());
		}
	}

	protected Topic testGetTopics_addTopic(Topic topic) throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetTopics() throws Exception {
		GraphQLField graphQLField = new GraphQLField(
			"topics",
			new HashMap<String, Object>() {
				{
					put("page", 1);
					put("pageSize", 10);
				}
			},
			new GraphQLField("items", getGraphQLFields()),
			new GraphQLField("page"), new GraphQLField("totalCount"));

		JSONObject topicsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/topics");

		long totalCount = topicsJSONObject.getLong("totalCount");

		Topic topic1 = testGraphQLTopic_addTopic();
		Topic topic2 = testGraphQLTopic_addTopic();

		topicsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/topics");

		Assert.assertEquals(
			totalCount + 2, topicsJSONObject.getLong("totalCount"));

		assertContains(
			topic1,
			Arrays.asList(
				TopicSerDes.toDTOs(topicsJSONObject.getString("items"))));
		assertContains(
			topic2,
			Arrays.asList(
				TopicSerDes.toDTOs(topicsJSONObject.getString("items"))));
	}

	@Test
	public void testCreateTopic() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteTopic() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		Topic topic = testDeleteTopic_addTopic();

		assertHttpResponseStatusCode(
			204, topicResource.deleteTopicHttpResponse(topic.getId()));
	}

	protected Topic testDeleteTopic_addTopic() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteTopic() throws Exception {
		Topic topic = testGraphQLTopic_addTopic();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteTopic",
						new HashMap<String, Object>() {
							{
								put("topicId", topic.getId());
							}
						})),
				"JSONObject/data", "Object/deleteTopic"));
	}

	@Test
	public void testGetTopicById() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testUpdateTopic() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetTopicsByStatus() throws Exception {
		String topicStatus = testGetTopicsByStatus_getTopicStatus();
		String irrelevantTopicStatus =
			testGetTopicsByStatus_getIrrelevantTopicStatus();

		Page<Topic> page = topicResource.getTopicsByStatus(topicStatus);

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantTopicStatus != null) {
			Topic irrelevantTopic = testGetTopicsByStatus_addTopic(
				irrelevantTopicStatus, randomIrrelevantTopic());

			page = topicResource.getTopicsByStatus(irrelevantTopicStatus);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantTopic), (List<Topic>)page.getItems());
			assertValid(page);
		}

		Topic topic1 = testGetTopicsByStatus_addTopic(
			topicStatus, randomTopic());

		Topic topic2 = testGetTopicsByStatus_addTopic(
			topicStatus, randomTopic());

		page = topicResource.getTopicsByStatus(topicStatus);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(topic1, topic2), (List<Topic>)page.getItems());
		assertValid(page);

		topicResource.deleteTopic(topic1.getId());

		topicResource.deleteTopic(topic2.getId());
	}

	protected Topic testGetTopicsByStatus_addTopic(
			String topicStatus, Topic topic)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetTopicsByStatus_getTopicStatus() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetTopicsByStatus_getIrrelevantTopicStatus()
		throws Exception {

		return null;
	}

	@Test
	public void testGetTopicWorkflowLogs() throws Exception {
		Long topicId = testGetTopicWorkflowLogs_getTopicId();
		Long irrelevantTopicId =
			testGetTopicWorkflowLogs_getIrrelevantTopicId();

		Page<Topic> page = topicResource.getTopicWorkflowLogs(topicId);

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantTopicId != null) {
			Topic irrelevantTopic = testGetTopicWorkflowLogs_addTopic(
				irrelevantTopicId, randomIrrelevantTopic());

			page = topicResource.getTopicWorkflowLogs(irrelevantTopicId);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantTopic), (List<Topic>)page.getItems());
			assertValid(page);
		}

		Topic topic1 = testGetTopicWorkflowLogs_addTopic(
			topicId, randomTopic());

		Topic topic2 = testGetTopicWorkflowLogs_addTopic(
			topicId, randomTopic());

		page = topicResource.getTopicWorkflowLogs(topicId);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(topic1, topic2), (List<Topic>)page.getItems());
		assertValid(page);

		topicResource.deleteTopic(topic1.getId());

		topicResource.deleteTopic(topic2.getId());
	}

	protected Topic testGetTopicWorkflowLogs_addTopic(Long topicId, Topic topic)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetTopicWorkflowLogs_getTopicId() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetTopicWorkflowLogs_getIrrelevantTopicId()
		throws Exception {

		return null;
	}

	protected Topic testGraphQLTopic_addTopic() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(Topic topic, List<Topic> topics) {
		boolean contains = false;

		for (Topic item : topics) {
			if (equals(topic, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(topics + " does not contain " + topic, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Topic topic1, Topic topic2) {
		Assert.assertTrue(
			topic1 + " does not equal " + topic2, equals(topic1, topic2));
	}

	protected void assertEquals(List<Topic> topics1, List<Topic> topics2) {
		Assert.assertEquals(topics1.size(), topics2.size());

		for (int i = 0; i < topics1.size(); i++) {
			Topic topic1 = topics1.get(i);
			Topic topic2 = topics2.get(i);

			assertEquals(topic1, topic2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Topic> topics1, List<Topic> topics2) {

		Assert.assertEquals(topics1.size(), topics2.size());

		for (Topic topic1 : topics1) {
			boolean contains = false;

			for (Topic topic2 : topics2) {
				if (equals(topic1, topic2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				topics2 + " does not contain " + topic1, contains);
		}
	}

	protected void assertValid(Topic topic) throws Exception {
		boolean valid = true;

		if (topic.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("meetingDates", additionalAssertFieldName)) {
				if (topic.getMeetingDates() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("numberOfMeetings", additionalAssertFieldName)) {
				if (topic.getNumberOfMeetings() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("topicAttachments", additionalAssertFieldName)) {
				if (topic.getTopicAttachments() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("topicDescription", additionalAssertFieldName)) {
				if (topic.getTopicDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("topicStatus", additionalAssertFieldName)) {
				if (topic.getTopicStatus() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("topicSubtitle", additionalAssertFieldName)) {
				if (topic.getTopicSubtitle() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("topicTitle", additionalAssertFieldName)) {
				if (topic.getTopicTitle() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Topic> page) {
		boolean valid = false;

		java.util.Collection<Topic> topics = page.getItems();

		int size = topics.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(com.ncema.dto.v1_0.Topic.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(Topic topic1, Topic topic2) {
		if (topic1 == topic2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(topic1.getId(), topic2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("meetingDates", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						topic1.getMeetingDates(), topic2.getMeetingDates())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("numberOfMeetings", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						topic1.getNumberOfMeetings(),
						topic2.getNumberOfMeetings())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("topicAttachments", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						topic1.getTopicAttachments(),
						topic2.getTopicAttachments())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("topicDescription", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						topic1.getTopicDescription(),
						topic2.getTopicDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("topicStatus", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						topic1.getTopicStatus(), topic2.getTopicStatus())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("topicSubtitle", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						topic1.getTopicSubtitle(), topic2.getTopicSubtitle())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("topicTitle", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						topic1.getTopicTitle(), topic2.getTopicTitle())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		Stream<java.lang.reflect.Field> stream = Stream.of(
			ReflectionUtil.getDeclaredFields(clazz));

		return stream.filter(
			field -> !field.isSynthetic()
		).toArray(
			java.lang.reflect.Field[]::new
		);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_topicResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_topicResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, Topic topic) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("meetingDates")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("numberOfMeetings")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("topicAttachments")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("topicDescription")) {
			sb.append("'");
			sb.append(String.valueOf(topic.getTopicDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("topicStatus")) {
			sb.append("'");
			sb.append(String.valueOf(topic.getTopicStatus()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("topicSubtitle")) {
			sb.append("'");
			sb.append(String.valueOf(topic.getTopicSubtitle()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("topicTitle")) {
			sb.append("'");
			sb.append(String.valueOf(topic.getTopicTitle()));
			sb.append("'");

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected Topic randomTopic() throws Exception {
		return new Topic() {
			{
				id = RandomTestUtil.randomLong();
				numberOfMeetings = RandomTestUtil.randomInt();
				topicDescription = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				topicStatus = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				topicSubtitle = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				topicTitle = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected Topic randomIrrelevantTopic() throws Exception {
		Topic randomIrrelevantTopic = randomTopic();

		return randomIrrelevantTopic;
	}

	protected Topic randomPatchTopic() throws Exception {
		return randomTopic();
	}

	protected TopicResource topicResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseTopicResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.ncema.resource.v1_0.TopicResource _topicResource;

}