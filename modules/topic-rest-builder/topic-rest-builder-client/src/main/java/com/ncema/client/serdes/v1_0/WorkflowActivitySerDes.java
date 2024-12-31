package com.ncema.client.serdes.v1_0;

import com.ncema.client.dto.v1_0.WorkflowActivity;
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
public class WorkflowActivitySerDes {

	public static WorkflowActivity toDTO(String json) {
		WorkflowActivityJSONParser workflowActivityJSONParser =
			new WorkflowActivityJSONParser();

		return workflowActivityJSONParser.parseToDTO(json);
	}

	public static WorkflowActivity[] toDTOs(String json) {
		WorkflowActivityJSONParser workflowActivityJSONParser =
			new WorkflowActivityJSONParser();

		return workflowActivityJSONParser.parseToDTOs(json);
	}

	public static String toJSON(WorkflowActivity workflowActivity) {
		if (workflowActivity == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (workflowActivity.getActivityType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"activityType\": ");

			sb.append("\"");

			sb.append(_escape(workflowActivity.getActivityType()));

			sb.append("\"");
		}

		if (workflowActivity.getComment() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"comment\": ");

			sb.append("\"");

			sb.append(_escape(workflowActivity.getComment()));

			sb.append("\"");
		}

		if (workflowActivity.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					workflowActivity.getCreateDate()));

			sb.append("\"");
		}

		if (workflowActivity.getCurrentNode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"currentNode\": ");

			sb.append("\"");

			sb.append(_escape(workflowActivity.getCurrentNode()));

			sb.append("\"");
		}

		if (workflowActivity.getIsTerminal() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"isTerminal\": ");

			sb.append(workflowActivity.getIsTerminal());
		}

		if (workflowActivity.getLogId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"logId\": ");

			sb.append(workflowActivity.getLogId());
		}

		if (workflowActivity.getPreviousNode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"previousNode\": ");

			sb.append("\"");

			sb.append(_escape(workflowActivity.getPreviousNode()));

			sb.append("\"");
		}

		if (workflowActivity.getTaskId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"taskId\": ");

			sb.append(workflowActivity.getTaskId());
		}

		if (workflowActivity.getUserId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userId\": ");

			sb.append(workflowActivity.getUserId());
		}

		if (workflowActivity.getUserName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userName\": ");

			sb.append("\"");

			sb.append(_escape(workflowActivity.getUserName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		WorkflowActivityJSONParser workflowActivityJSONParser =
			new WorkflowActivityJSONParser();

		return workflowActivityJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(WorkflowActivity workflowActivity) {
		if (workflowActivity == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (workflowActivity.getActivityType() == null) {
			map.put("activityType", null);
		}
		else {
			map.put(
				"activityType",
				String.valueOf(workflowActivity.getActivityType()));
		}

		if (workflowActivity.getComment() == null) {
			map.put("comment", null);
		}
		else {
			map.put("comment", String.valueOf(workflowActivity.getComment()));
		}

		if (workflowActivity.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(
					workflowActivity.getCreateDate()));
		}

		if (workflowActivity.getCurrentNode() == null) {
			map.put("currentNode", null);
		}
		else {
			map.put(
				"currentNode",
				String.valueOf(workflowActivity.getCurrentNode()));
		}

		if (workflowActivity.getIsTerminal() == null) {
			map.put("isTerminal", null);
		}
		else {
			map.put(
				"isTerminal", String.valueOf(workflowActivity.getIsTerminal()));
		}

		if (workflowActivity.getLogId() == null) {
			map.put("logId", null);
		}
		else {
			map.put("logId", String.valueOf(workflowActivity.getLogId()));
		}

		if (workflowActivity.getPreviousNode() == null) {
			map.put("previousNode", null);
		}
		else {
			map.put(
				"previousNode",
				String.valueOf(workflowActivity.getPreviousNode()));
		}

		if (workflowActivity.getTaskId() == null) {
			map.put("taskId", null);
		}
		else {
			map.put("taskId", String.valueOf(workflowActivity.getTaskId()));
		}

		if (workflowActivity.getUserId() == null) {
			map.put("userId", null);
		}
		else {
			map.put("userId", String.valueOf(workflowActivity.getUserId()));
		}

		if (workflowActivity.getUserName() == null) {
			map.put("userName", null);
		}
		else {
			map.put("userName", String.valueOf(workflowActivity.getUserName()));
		}

		return map;
	}

	public static class WorkflowActivityJSONParser
		extends BaseJSONParser<WorkflowActivity> {

		@Override
		protected WorkflowActivity createDTO() {
			return new WorkflowActivity();
		}

		@Override
		protected WorkflowActivity[] createDTOArray(int size) {
			return new WorkflowActivity[size];
		}

		@Override
		protected void setField(
			WorkflowActivity workflowActivity, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "activityType")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setActivityType(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "comment")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setComment((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setCreateDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "currentNode")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setCurrentNode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "isTerminal")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setIsTerminal(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "logId")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setLogId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "previousNode")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setPreviousNode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "taskId")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setTaskId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userId")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setUserId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userName")) {
				if (jsonParserFieldValue != null) {
					workflowActivity.setUserName((String)jsonParserFieldValue);
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