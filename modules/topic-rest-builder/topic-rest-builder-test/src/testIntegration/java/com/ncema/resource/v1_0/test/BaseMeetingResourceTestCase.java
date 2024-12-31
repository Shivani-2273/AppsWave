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
import com.liferay.portal.search.test.util.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import com.ncema.client.dto.v1_0.Meeting;
import com.ncema.client.http.HttpInvoker;
import com.ncema.client.pagination.Page;
import com.ncema.client.pagination.Pagination;
import com.ncema.client.resource.v1_0.MeetingResource;
import com.ncema.client.serdes.v1_0.MeetingSerDes;

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
public abstract class BaseMeetingResourceTestCase {

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

		_meetingResource.setContextCompany(testCompany);

		MeetingResource.Builder builder = MeetingResource.builder();

		meetingResource = builder.authentication(
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

		Meeting meeting1 = randomMeeting();

		String json = objectMapper.writeValueAsString(meeting1);

		Meeting meeting2 = MeetingSerDes.toDTO(json);

		Assert.assertTrue(equals(meeting1, meeting2));
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

		Meeting meeting = randomMeeting();

		String json1 = objectMapper.writeValueAsString(meeting);
		String json2 = MeetingSerDes.toJSON(meeting);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Meeting meeting = randomMeeting();

		meeting.setMeetingLink(regex);
		meeting.setMeetingLocation(regex);
		meeting.setMeetingTitle(regex);

		String json = MeetingSerDes.toJSON(meeting);

		Assert.assertFalse(json.contains(regex));

		meeting = MeetingSerDes.toDTO(json);

		Assert.assertEquals(regex, meeting.getMeetingLink());
		Assert.assertEquals(regex, meeting.getMeetingLocation());
		Assert.assertEquals(regex, meeting.getMeetingTitle());
	}

	@Test
	public void testGetMeetings() throws Exception {
		Page<Meeting> page = meetingResource.getMeetings(
			null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		Meeting meeting1 = testGetMeetings_addMeeting(randomMeeting());

		Meeting meeting2 = testGetMeetings_addMeeting(randomMeeting());

		page = meetingResource.getMeetings(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(meeting1, (List<Meeting>)page.getItems());
		assertContains(meeting2, (List<Meeting>)page.getItems());
		assertValid(page);

		meetingResource.deleteMeeting(meeting1.getId());

		meetingResource.deleteMeeting(meeting2.getId());
	}

	@Test
	public void testGetMeetingsWithFilterDateTimeEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Meeting meeting1 = randomMeeting();

		meeting1 = testGetMeetings_addMeeting(meeting1);

		for (EntityField entityField : entityFields) {
			Page<Meeting> page = meetingResource.getMeetings(
				null, getFilterString(entityField, "between", meeting1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(meeting1),
				(List<Meeting>)page.getItems());
		}
	}

	@Test
	public void testGetMeetingsWithFilterStringEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Meeting meeting1 = testGetMeetings_addMeeting(randomMeeting());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Meeting meeting2 = testGetMeetings_addMeeting(randomMeeting());

		for (EntityField entityField : entityFields) {
			Page<Meeting> page = meetingResource.getMeetings(
				null, getFilterString(entityField, "eq", meeting1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(meeting1),
				(List<Meeting>)page.getItems());
		}
	}

	@Test
	public void testGetMeetingsWithPagination() throws Exception {
		Page<Meeting> totalPage = meetingResource.getMeetings(
			null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		Meeting meeting1 = testGetMeetings_addMeeting(randomMeeting());

		Meeting meeting2 = testGetMeetings_addMeeting(randomMeeting());

		Meeting meeting3 = testGetMeetings_addMeeting(randomMeeting());

		Page<Meeting> page1 = meetingResource.getMeetings(
			null, null, Pagination.of(1, totalCount + 2), null);

		List<Meeting> meetings1 = (List<Meeting>)page1.getItems();

		Assert.assertEquals(
			meetings1.toString(), totalCount + 2, meetings1.size());

		Page<Meeting> page2 = meetingResource.getMeetings(
			null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<Meeting> meetings2 = (List<Meeting>)page2.getItems();

		Assert.assertEquals(meetings2.toString(), 1, meetings2.size());

		Page<Meeting> page3 = meetingResource.getMeetings(
			null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(meeting1, (List<Meeting>)page3.getItems());
		assertContains(meeting2, (List<Meeting>)page3.getItems());
		assertContains(meeting3, (List<Meeting>)page3.getItems());
	}

	@Test
	public void testGetMeetingsWithSortDateTime() throws Exception {
		testGetMeetingsWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, meeting1, meeting2) -> {
				BeanUtils.setProperty(
					meeting1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetMeetingsWithSortInteger() throws Exception {
		testGetMeetingsWithSort(
			EntityField.Type.INTEGER,
			(entityField, meeting1, meeting2) -> {
				BeanUtils.setProperty(meeting1, entityField.getName(), 0);
				BeanUtils.setProperty(meeting2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetMeetingsWithSortString() throws Exception {
		testGetMeetingsWithSort(
			EntityField.Type.STRING,
			(entityField, meeting1, meeting2) -> {
				Class<?> clazz = meeting1.getClass();

				String entityFieldName = entityField.getName();

				java.lang.reflect.Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						meeting1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						meeting2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanUtils.setProperty(
						meeting1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanUtils.setProperty(
						meeting2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanUtils.setProperty(
						meeting1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanUtils.setProperty(
						meeting2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetMeetingsWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, Meeting, Meeting, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Meeting meeting1 = randomMeeting();
		Meeting meeting2 = randomMeeting();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, meeting1, meeting2);
		}

		meeting1 = testGetMeetings_addMeeting(meeting1);

		meeting2 = testGetMeetings_addMeeting(meeting2);

		for (EntityField entityField : entityFields) {
			Page<Meeting> ascPage = meetingResource.getMeetings(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(meeting1, meeting2),
				(List<Meeting>)ascPage.getItems());

			Page<Meeting> descPage = meetingResource.getMeetings(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(meeting2, meeting1),
				(List<Meeting>)descPage.getItems());
		}
	}

	protected Meeting testGetMeetings_addMeeting(Meeting meeting)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetMeetings() throws Exception {
		GraphQLField graphQLField = new GraphQLField(
			"meetings",
			new HashMap<String, Object>() {
				{
					put("page", 1);
					put("pageSize", 10);
				}
			},
			new GraphQLField("items", getGraphQLFields()),
			new GraphQLField("page"), new GraphQLField("totalCount"));

		JSONObject meetingsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/meetings");

		long totalCount = meetingsJSONObject.getLong("totalCount");

		Meeting meeting1 = testGraphQLMeeting_addMeeting();
		Meeting meeting2 = testGraphQLMeeting_addMeeting();

		meetingsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/meetings");

		Assert.assertEquals(
			totalCount + 2, meetingsJSONObject.getLong("totalCount"));

		assertContains(
			meeting1,
			Arrays.asList(
				MeetingSerDes.toDTOs(meetingsJSONObject.getString("items"))));
		assertContains(
			meeting2,
			Arrays.asList(
				MeetingSerDes.toDTOs(meetingsJSONObject.getString("items"))));
	}

	@Test
	public void testCreateMeeting() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteMeeting() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		Meeting meeting = testDeleteMeeting_addMeeting();

		assertHttpResponseStatusCode(
			204, meetingResource.deleteMeetingHttpResponse(meeting.getId()));
	}

	protected Meeting testDeleteMeeting_addMeeting() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteMeeting() throws Exception {
		Meeting meeting = testGraphQLMeeting_addMeeting();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteMeeting",
						new HashMap<String, Object>() {
							{
								put("meetingId", meeting.getId());
							}
						})),
				"JSONObject/data", "Object/deleteMeeting"));
	}

	@Test
	public void testGetMeetingById() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testUpdateMeeting() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetMeetingParticipants() throws Exception {
		Long meetingId = testGetMeetingParticipants_getMeetingId();
		Long irrelevantMeetingId =
			testGetMeetingParticipants_getIrrelevantMeetingId();

		Page<Meeting> page = meetingResource.getMeetingParticipants(meetingId);

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantMeetingId != null) {
			Meeting irrelevantMeeting = testGetMeetingParticipants_addMeeting(
				irrelevantMeetingId, randomIrrelevantMeeting());

			page = meetingResource.getMeetingParticipants(irrelevantMeetingId);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantMeeting),
				(List<Meeting>)page.getItems());
			assertValid(page);
		}

		Meeting meeting1 = testGetMeetingParticipants_addMeeting(
			meetingId, randomMeeting());

		Meeting meeting2 = testGetMeetingParticipants_addMeeting(
			meetingId, randomMeeting());

		page = meetingResource.getMeetingParticipants(meetingId);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(meeting1, meeting2), (List<Meeting>)page.getItems());
		assertValid(page);

		meetingResource.deleteMeeting(meeting1.getId());

		meetingResource.deleteMeeting(meeting2.getId());
	}

	protected Meeting testGetMeetingParticipants_addMeeting(
			Long meetingId, Meeting meeting)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetMeetingParticipants_getMeetingId() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetMeetingParticipants_getIrrelevantMeetingId()
		throws Exception {

		return null;
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected Meeting testGraphQLMeeting_addMeeting() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(Meeting meeting, List<Meeting> meetings) {
		boolean contains = false;

		for (Meeting item : meetings) {
			if (equals(meeting, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(meetings + " does not contain " + meeting, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Meeting meeting1, Meeting meeting2) {
		Assert.assertTrue(
			meeting1 + " does not equal " + meeting2,
			equals(meeting1, meeting2));
	}

	protected void assertEquals(
		List<Meeting> meetings1, List<Meeting> meetings2) {

		Assert.assertEquals(meetings1.size(), meetings2.size());

		for (int i = 0; i < meetings1.size(); i++) {
			Meeting meeting1 = meetings1.get(i);
			Meeting meeting2 = meetings2.get(i);

			assertEquals(meeting1, meeting2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Meeting> meetings1, List<Meeting> meetings2) {

		Assert.assertEquals(meetings1.size(), meetings2.size());

		for (Meeting meeting1 : meetings1) {
			boolean contains = false;

			for (Meeting meeting2 : meetings2) {
				if (equals(meeting1, meeting2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				meetings2 + " does not contain " + meeting1, contains);
		}
	}

	protected void assertValid(Meeting meeting) throws Exception {
		boolean valid = true;

		if (meeting.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("meetingDateTime", additionalAssertFieldName)) {
				if (meeting.getMeetingDateTime() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("meetingLink", additionalAssertFieldName)) {
				if (meeting.getMeetingLink() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("meetingLocation", additionalAssertFieldName)) {
				if (meeting.getMeetingLocation() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("meetingNumber", additionalAssertFieldName)) {
				if (meeting.getMeetingNumber() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("meetingTitle", additionalAssertFieldName)) {
				if (meeting.getMeetingTitle() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("participants", additionalAssertFieldName)) {
				if (meeting.getParticipants() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("topics", additionalAssertFieldName)) {
				if (meeting.getTopics() == null) {
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

	protected void assertValid(Page<Meeting> page) {
		boolean valid = false;

		java.util.Collection<Meeting> meetings = page.getItems();

		int size = meetings.size();

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
				getDeclaredFields(com.ncema.dto.v1_0.Meeting.class)) {

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

	protected boolean equals(Meeting meeting1, Meeting meeting2) {
		if (meeting1 == meeting2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(meeting1.getId(), meeting2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("meetingDateTime", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						meeting1.getMeetingDateTime(),
						meeting2.getMeetingDateTime())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("meetingLink", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						meeting1.getMeetingLink(), meeting2.getMeetingLink())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("meetingLocation", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						meeting1.getMeetingLocation(),
						meeting2.getMeetingLocation())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("meetingNumber", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						meeting1.getMeetingNumber(),
						meeting2.getMeetingNumber())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("meetingTitle", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						meeting1.getMeetingTitle(),
						meeting2.getMeetingTitle())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("participants", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						meeting1.getParticipants(),
						meeting2.getParticipants())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("topics", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						meeting1.getTopics(), meeting2.getTopics())) {

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

		if (!(_meetingResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_meetingResource;

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
		EntityField entityField, String operator, Meeting meeting) {

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

		if (entityFieldName.equals("meetingDateTime")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							meeting.getMeetingDateTime(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(meeting.getMeetingDateTime(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(meeting.getMeetingDateTime()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("meetingLink")) {
			sb.append("'");
			sb.append(String.valueOf(meeting.getMeetingLink()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("meetingLocation")) {
			sb.append("'");
			sb.append(String.valueOf(meeting.getMeetingLocation()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("meetingNumber")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("meetingTitle")) {
			sb.append("'");
			sb.append(String.valueOf(meeting.getMeetingTitle()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("participants")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("topics")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
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

	protected Meeting randomMeeting() throws Exception {
		return new Meeting() {
			{
				id = RandomTestUtil.randomLong();
				meetingDateTime = RandomTestUtil.nextDate();
				meetingLink = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				meetingLocation = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				meetingNumber = RandomTestUtil.randomLong();
				meetingTitle = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected Meeting randomIrrelevantMeeting() throws Exception {
		Meeting randomIrrelevantMeeting = randomMeeting();

		return randomIrrelevantMeeting;
	}

	protected Meeting randomPatchMeeting() throws Exception {
		return randomMeeting();
	}

	protected MeetingResource meetingResource;
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
		LogFactoryUtil.getLog(BaseMeetingResourceTestCase.class);

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
	private com.ncema.resource.v1_0.MeetingResource _meetingResource;

}