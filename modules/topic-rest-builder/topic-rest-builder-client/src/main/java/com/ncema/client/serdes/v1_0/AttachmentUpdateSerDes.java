package com.ncema.client.serdes.v1_0;

import com.ncema.client.dto.v1_0.AttachmentUpdate;
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
public class AttachmentUpdateSerDes {

	public static AttachmentUpdate toDTO(String json) {
		AttachmentUpdateJSONParser attachmentUpdateJSONParser =
			new AttachmentUpdateJSONParser();

		return attachmentUpdateJSONParser.parseToDTO(json);
	}

	public static AttachmentUpdate[] toDTOs(String json) {
		AttachmentUpdateJSONParser attachmentUpdateJSONParser =
			new AttachmentUpdateJSONParser();

		return attachmentUpdateJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AttachmentUpdate attachmentUpdate) {
		if (attachmentUpdate == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (attachmentUpdate.getFileBase64() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fileBase64\": ");

			sb.append("\"");

			sb.append(_escape(attachmentUpdate.getFileBase64()));

			sb.append("\"");
		}

		if (attachmentUpdate.getFileName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fileName\": ");

			sb.append("\"");

			sb.append(_escape(attachmentUpdate.getFileName()));

			sb.append("\"");
		}

		if (attachmentUpdate.getMimeType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"mimeType\": ");

			sb.append("\"");

			sb.append(_escape(attachmentUpdate.getMimeType()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AttachmentUpdateJSONParser attachmentUpdateJSONParser =
			new AttachmentUpdateJSONParser();

		return attachmentUpdateJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AttachmentUpdate attachmentUpdate) {
		if (attachmentUpdate == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (attachmentUpdate.getFileBase64() == null) {
			map.put("fileBase64", null);
		}
		else {
			map.put(
				"fileBase64", String.valueOf(attachmentUpdate.getFileBase64()));
		}

		if (attachmentUpdate.getFileName() == null) {
			map.put("fileName", null);
		}
		else {
			map.put("fileName", String.valueOf(attachmentUpdate.getFileName()));
		}

		if (attachmentUpdate.getMimeType() == null) {
			map.put("mimeType", null);
		}
		else {
			map.put("mimeType", String.valueOf(attachmentUpdate.getMimeType()));
		}

		return map;
	}

	public static class AttachmentUpdateJSONParser
		extends BaseJSONParser<AttachmentUpdate> {

		@Override
		protected AttachmentUpdate createDTO() {
			return new AttachmentUpdate();
		}

		@Override
		protected AttachmentUpdate[] createDTOArray(int size) {
			return new AttachmentUpdate[size];
		}

		@Override
		protected void setField(
			AttachmentUpdate attachmentUpdate, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "fileBase64")) {
				if (jsonParserFieldValue != null) {
					attachmentUpdate.setFileBase64(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fileName")) {
				if (jsonParserFieldValue != null) {
					attachmentUpdate.setFileName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "mimeType")) {
				if (jsonParserFieldValue != null) {
					attachmentUpdate.setMimeType((String)jsonParserFieldValue);
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