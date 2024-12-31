package com.ncema.client.serdes.v1_0;

import com.ncema.client.dto.v1_0.Topic;
import com.ncema.client.dto.v1_0.TopicAttachment;
import com.ncema.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author root316
 * @generated
 */
@Generated("")
public class TopicSerDes {

	public static Topic toDTO(String json) {
		TopicJSONParser topicJSONParser = new TopicJSONParser();

		return topicJSONParser.parseToDTO(json);
	}

	public static Topic[] toDTOs(String json) {
		TopicJSONParser topicJSONParser = new TopicJSONParser();

		return topicJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Topic topic) {
		if (topic == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (topic.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(topic.getId());
		}

		if (topic.getMeetingDates() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingDates\": ");

			sb.append("[");

			for (int i = 0; i < topic.getMeetingDates().length; i++) {
				sb.append("\"");

				sb.append(
					liferayToJSONDateFormat.format(topic.getMeetingDates()[i]));

				sb.append("\"");

				if ((i + 1) < topic.getMeetingDates().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (topic.getNumberOfMeetings() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"numberOfMeetings\": ");

			sb.append(topic.getNumberOfMeetings());
		}

		if (topic.getTopicAttachments() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicAttachments\": ");

			sb.append("[");

			for (int i = 0; i < topic.getTopicAttachments().length; i++) {
				sb.append(String.valueOf(topic.getTopicAttachments()[i]));

				if ((i + 1) < topic.getTopicAttachments().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (topic.getTopicDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicDescription\": ");

			sb.append("\"");

			sb.append(_escape(topic.getTopicDescription()));

			sb.append("\"");
		}

		if (topic.getTopicStatus() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicStatus\": ");

			sb.append("\"");

			sb.append(_escape(topic.getTopicStatus()));

			sb.append("\"");
		}

		if (topic.getTopicSubtitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicSubtitle\": ");

			sb.append("\"");

			sb.append(_escape(topic.getTopicSubtitle()));

			sb.append("\"");
		}

		if (topic.getTopicTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicTitle\": ");

			sb.append("\"");

			sb.append(_escape(topic.getTopicTitle()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TopicJSONParser topicJSONParser = new TopicJSONParser();

		return topicJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Topic topic) {
		if (topic == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (topic.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(topic.getId()));
		}

		if (topic.getMeetingDates() == null) {
			map.put("meetingDates", null);
		}
		else {
			map.put("meetingDates", String.valueOf(topic.getMeetingDates()));
		}

		if (topic.getNumberOfMeetings() == null) {
			map.put("numberOfMeetings", null);
		}
		else {
			map.put(
				"numberOfMeetings",
				String.valueOf(topic.getNumberOfMeetings()));
		}

		if (topic.getTopicAttachments() == null) {
			map.put("topicAttachments", null);
		}
		else {
			map.put(
				"topicAttachments",
				String.valueOf(topic.getTopicAttachments()));
		}

		if (topic.getTopicDescription() == null) {
			map.put("topicDescription", null);
		}
		else {
			map.put(
				"topicDescription",
				String.valueOf(topic.getTopicDescription()));
		}

		if (topic.getTopicStatus() == null) {
			map.put("topicStatus", null);
		}
		else {
			map.put("topicStatus", String.valueOf(topic.getTopicStatus()));
		}

		if (topic.getTopicSubtitle() == null) {
			map.put("topicSubtitle", null);
		}
		else {
			map.put("topicSubtitle", String.valueOf(topic.getTopicSubtitle()));
		}

		if (topic.getTopicTitle() == null) {
			map.put("topicTitle", null);
		}
		else {
			map.put("topicTitle", String.valueOf(topic.getTopicTitle()));
		}

		return map;
	}

	public static class TopicJSONParser extends BaseJSONParser<Topic> {

		@Override
		protected Topic createDTO() {
			return new Topic();
		}

		@Override
		protected Topic[] createDTOArray(int size) {
			return new Topic[size];
		}

		@Override
		protected void setField(
			Topic topic, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					topic.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingDates")) {
				if (jsonParserFieldValue != null) {
					topic.setMeetingDates(
						toDates((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "numberOfMeetings")) {
				if (jsonParserFieldValue != null) {
					topic.setNumberOfMeetings(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "topicAttachments")) {
				if (jsonParserFieldValue != null) {
					topic.setTopicAttachments(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> TopicAttachmentSerDes.toDTO(
								(String)object)
						).toArray(
							size -> new TopicAttachment[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "topicDescription")) {
				if (jsonParserFieldValue != null) {
					topic.setTopicDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "topicStatus")) {
				if (jsonParserFieldValue != null) {
					topic.setTopicStatus((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "topicSubtitle")) {
				if (jsonParserFieldValue != null) {
					topic.setTopicSubtitle((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "topicTitle")) {
				if (jsonParserFieldValue != null) {
					topic.setTopicTitle((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}