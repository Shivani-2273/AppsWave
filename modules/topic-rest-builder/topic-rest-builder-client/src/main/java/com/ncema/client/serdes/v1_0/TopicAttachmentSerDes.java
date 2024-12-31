package com.ncema.client.serdes.v1_0;

import com.ncema.client.dto.v1_0.TopicAttachment;
import com.ncema.client.json.BaseJSONParser;

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
public class TopicAttachmentSerDes {

	public static TopicAttachment toDTO(String json) {
		TopicAttachmentJSONParser topicAttachmentJSONParser =
			new TopicAttachmentJSONParser();

		return topicAttachmentJSONParser.parseToDTO(json);
	}

	public static TopicAttachment[] toDTOs(String json) {
		TopicAttachmentJSONParser topicAttachmentJSONParser =
			new TopicAttachmentJSONParser();

		return topicAttachmentJSONParser.parseToDTOs(json);
	}

	public static String toJSON(TopicAttachment topicAttachment) {
		if (topicAttachment == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (topicAttachment.getAttachmentId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"attachmentId\": ");

			sb.append(topicAttachment.getAttachmentId());
		}

		if (topicAttachment.getFileBase64() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fileBase64\": ");

			sb.append("\"");

			sb.append(_escape(topicAttachment.getFileBase64()));

			sb.append("\"");
		}

		if (topicAttachment.getFileName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fileName\": ");

			sb.append("\"");

			sb.append(_escape(topicAttachment.getFileName()));

			sb.append("\"");
		}

		if (topicAttachment.getMimeType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"mimeType\": ");

			sb.append("\"");

			sb.append(_escape(topicAttachment.getMimeType()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TopicAttachmentJSONParser topicAttachmentJSONParser =
			new TopicAttachmentJSONParser();

		return topicAttachmentJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(TopicAttachment topicAttachment) {
		if (topicAttachment == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (topicAttachment.getAttachmentId() == null) {
			map.put("attachmentId", null);
		}
		else {
			map.put(
				"attachmentId",
				String.valueOf(topicAttachment.getAttachmentId()));
		}

		if (topicAttachment.getFileBase64() == null) {
			map.put("fileBase64", null);
		}
		else {
			map.put(
				"fileBase64", String.valueOf(topicAttachment.getFileBase64()));
		}

		if (topicAttachment.getFileName() == null) {
			map.put("fileName", null);
		}
		else {
			map.put("fileName", String.valueOf(topicAttachment.getFileName()));
		}

		if (topicAttachment.getMimeType() == null) {
			map.put("mimeType", null);
		}
		else {
			map.put("mimeType", String.valueOf(topicAttachment.getMimeType()));
		}

		return map;
	}

	public static class TopicAttachmentJSONParser
		extends BaseJSONParser<TopicAttachment> {

		@Override
		protected TopicAttachment createDTO() {
			return new TopicAttachment();
		}

		@Override
		protected TopicAttachment[] createDTOArray(int size) {
			return new TopicAttachment[size];
		}

		@Override
		protected void setField(
			TopicAttachment topicAttachment, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "attachmentId")) {
				if (jsonParserFieldValue != null) {
					topicAttachment.setAttachmentId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fileBase64")) {
				if (jsonParserFieldValue != null) {
					topicAttachment.setFileBase64((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fileName")) {
				if (jsonParserFieldValue != null) {
					topicAttachment.setFileName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "mimeType")) {
				if (jsonParserFieldValue != null) {
					topicAttachment.setMimeType((String)jsonParserFieldValue);
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