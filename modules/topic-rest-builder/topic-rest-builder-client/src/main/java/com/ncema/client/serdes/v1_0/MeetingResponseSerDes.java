package com.ncema.client.serdes.v1_0;

import com.ncema.client.dto.v1_0.MeetingResponse;
import com.ncema.client.dto.v1_0.Topic;
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
public class MeetingResponseSerDes {

	public static MeetingResponse toDTO(String json) {
		MeetingResponseJSONParser meetingResponseJSONParser =
			new MeetingResponseJSONParser();

		return meetingResponseJSONParser.parseToDTO(json);
	}

	public static MeetingResponse[] toDTOs(String json) {
		MeetingResponseJSONParser meetingResponseJSONParser =
			new MeetingResponseJSONParser();

		return meetingResponseJSONParser.parseToDTOs(json);
	}

	public static String toJSON(MeetingResponse meetingResponse) {
		if (meetingResponse == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (meetingResponse.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(meetingResponse.getId());
		}

		if (meetingResponse.getMeetingDateTime() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingDateTime\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					meetingResponse.getMeetingDateTime()));

			sb.append("\"");
		}

		if (meetingResponse.getMeetingLink() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingLink\": ");

			sb.append("\"");

			sb.append(_escape(meetingResponse.getMeetingLink()));

			sb.append("\"");
		}

		if (meetingResponse.getMeetingLocation() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingLocation\": ");

			sb.append("\"");

			sb.append(_escape(meetingResponse.getMeetingLocation()));

			sb.append("\"");
		}

		if (meetingResponse.getMeetingNumber() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingNumber\": ");

			sb.append(meetingResponse.getMeetingNumber());
		}

		if (meetingResponse.getMeetingTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingTitle\": ");

			sb.append("\"");

			sb.append(_escape(meetingResponse.getMeetingTitle()));

			sb.append("\"");
		}

		if (meetingResponse.getParticipants() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"participants\": ");

			sb.append("[");

			for (int i = 0; i < meetingResponse.getParticipants().length; i++) {
				sb.append(meetingResponse.getParticipants()[i]);

				if ((i + 1) < meetingResponse.getParticipants().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (meetingResponse.getTopics() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topics\": ");

			sb.append("[");

			for (int i = 0; i < meetingResponse.getTopics().length; i++) {
				sb.append(String.valueOf(meetingResponse.getTopics()[i]));

				if ((i + 1) < meetingResponse.getTopics().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		MeetingResponseJSONParser meetingResponseJSONParser =
			new MeetingResponseJSONParser();

		return meetingResponseJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(MeetingResponse meetingResponse) {
		if (meetingResponse == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (meetingResponse.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(meetingResponse.getId()));
		}

		if (meetingResponse.getMeetingDateTime() == null) {
			map.put("meetingDateTime", null);
		}
		else {
			map.put(
				"meetingDateTime",
				liferayToJSONDateFormat.format(
					meetingResponse.getMeetingDateTime()));
		}

		if (meetingResponse.getMeetingLink() == null) {
			map.put("meetingLink", null);
		}
		else {
			map.put(
				"meetingLink",
				String.valueOf(meetingResponse.getMeetingLink()));
		}

		if (meetingResponse.getMeetingLocation() == null) {
			map.put("meetingLocation", null);
		}
		else {
			map.put(
				"meetingLocation",
				String.valueOf(meetingResponse.getMeetingLocation()));
		}

		if (meetingResponse.getMeetingNumber() == null) {
			map.put("meetingNumber", null);
		}
		else {
			map.put(
				"meetingNumber",
				String.valueOf(meetingResponse.getMeetingNumber()));
		}

		if (meetingResponse.getMeetingTitle() == null) {
			map.put("meetingTitle", null);
		}
		else {
			map.put(
				"meetingTitle",
				String.valueOf(meetingResponse.getMeetingTitle()));
		}

		if (meetingResponse.getParticipants() == null) {
			map.put("participants", null);
		}
		else {
			map.put(
				"participants",
				String.valueOf(meetingResponse.getParticipants()));
		}

		if (meetingResponse.getTopics() == null) {
			map.put("topics", null);
		}
		else {
			map.put("topics", String.valueOf(meetingResponse.getTopics()));
		}

		return map;
	}

	public static class MeetingResponseJSONParser
		extends BaseJSONParser<MeetingResponse> {

		@Override
		protected MeetingResponse createDTO() {
			return new MeetingResponse();
		}

		@Override
		protected MeetingResponse[] createDTOArray(int size) {
			return new MeetingResponse[size];
		}

		@Override
		protected void setField(
			MeetingResponse meetingResponse, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					meetingResponse.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingDateTime")) {
				if (jsonParserFieldValue != null) {
					meetingResponse.setMeetingDateTime(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingLink")) {
				if (jsonParserFieldValue != null) {
					meetingResponse.setMeetingLink(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingLocation")) {
				if (jsonParserFieldValue != null) {
					meetingResponse.setMeetingLocation(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingNumber")) {
				if (jsonParserFieldValue != null) {
					meetingResponse.setMeetingNumber(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "meetingTitle")) {
				if (jsonParserFieldValue != null) {
					meetingResponse.setMeetingTitle(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "participants")) {
				if (jsonParserFieldValue != null) {
					meetingResponse.setParticipants(
						toLongs((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "topics")) {
				if (jsonParserFieldValue != null) {
					meetingResponse.setTopics(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> TopicSerDes.toDTO((String)object)
						).toArray(
							size -> new Topic[size]
						));
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