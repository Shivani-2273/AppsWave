package com.ncema.client.serdes.v1_0;

import com.ncema.client.dto.v1_0.Meeting;
import com.ncema.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author root316
 * @generated
 */
@Generated("")
public class MeetingSerDes {

	public static Meeting toDTO(String json) {
		MeetingJSONParser meetingJSONParser = new MeetingJSONParser();

		return meetingJSONParser.parseToDTO(json);
	}

	public static Meeting[] toDTOs(String json) {
		MeetingJSONParser meetingJSONParser = new MeetingJSONParser();

		return meetingJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Meeting meeting) {
		if (meeting == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (meeting.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(meeting.getId());
		}

		if (meeting.getMeetingDateTime() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingDateTime\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(meeting.getMeetingDateTime()));

			sb.append("\"");
		}

		if (meeting.getMeetingLink() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingLink\": ");

			sb.append("\"");

			sb.append(_escape(meeting.getMeetingLink()));

			sb.append("\"");
		}

		if (meeting.getMeetingLocation() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingLocation\": ");

			sb.append("\"");

			sb.append(_escape(meeting.getMeetingLocation()));

			sb.append("\"");
		}

		if (meeting.getMeetingNumber() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingNumber\": ");

			sb.append(meeting.getMeetingNumber());
		}

		if (meeting.getMeetingTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingTitle\": ");

			sb.append("\"");

			sb.append(_escape(meeting.getMeetingTitle()));

			sb.append("\"");
		}

		if (meeting.getParticipants() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"participants\": ");

			sb.append("[");

			for (int i = 0; i < meeting.getParticipants().length; i++) {
				sb.append(meeting.getParticipants()[i]);

				if ((i + 1) < meeting.getParticipants().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (meeting.getTopics() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topics\": ");

			sb.append("[");

			for (int i = 0; i < meeting.getTopics().length; i++) {
				sb.append(meeting.getTopics()[i]);

				if ((i + 1) < meeting.getTopics().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		MeetingJSONParser meetingJSONParser = new MeetingJSONParser();

		return meetingJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Meeting meeting) {
		if (meeting == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (meeting.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(meeting.getId()));
		}

		if (meeting.getMeetingDateTime() == null) {
			map.put("meetingDateTime", null);
		}
		else {
			map.put(
				"meetingDateTime",
				liferayToJSONDateFormat.format(meeting.getMeetingDateTime()));
		}

		if (meeting.getMeetingLink() == null) {
			map.put("meetingLink", null);
		}
		else {
			map.put("meetingLink", String.valueOf(meeting.getMeetingLink()));
		}

		if (meeting.getMeetingLocation() == null) {
			map.put("meetingLocation", null);
		}
		else {
			map.put(
				"meetingLocation",
				String.valueOf(meeting.getMeetingLocation()));
		}

		if (meeting.getMeetingNumber() == null) {
			map.put("meetingNumber", null);
		}
		else {
			map.put(
				"meetingNumber", String.valueOf(meeting.getMeetingNumber()));
		}

		if (meeting.getMeetingTitle() == null) {
			map.put("meetingTitle", null);
		}
		else {
			map.put("meetingTitle", String.valueOf(meeting.getMeetingTitle()));
		}

		if (meeting.getParticipants() == null) {
			map.put("participants", null);
		}
		else {
			map.put("participants", String.valueOf(meeting.getParticipants()));
		}

		if (meeting.getTopics() == null) {
			map.put("topics", null);
		}
		else {
			map.put("topics", String.valueOf(meeting.getTopics()));
		}

		return map;
	}

	public static class MeetingJSONParser extends BaseJSONParser<Meeting> {

		@Override
		protected Meeting createDTO() {
			return new Meeting();
		}

		@Override
		protected Meeting[] createDTOArray(int size) {
			return new Meeting[size];
		}

		@Override
		protected void setField(
			Meeting meeting, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					meeting.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingDateTime")) {
				if (jsonParserFieldValue != null) {
					meeting.setMeetingDateTime(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingLink")) {
				if (jsonParserFieldValue != null) {
					meeting.setMeetingLink((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingLocation")) {
				if (jsonParserFieldValue != null) {
					meeting.setMeetingLocation((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingNumber")) {
				if (jsonParserFieldValue != null) {
					meeting.setMeetingNumber(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingTitle")) {
				if (jsonParserFieldValue != null) {
					meeting.setMeetingTitle((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "participants")) {
				if (jsonParserFieldValue != null) {
					meeting.setParticipants(
						toLongs((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "topics")) {
				if (jsonParserFieldValue != null) {
					meeting.setTopics(toLongs((Object[])jsonParserFieldValue));
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