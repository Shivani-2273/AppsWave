package com.ncema.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author root316
 * @generated
 */
@Generated("")
@GraphQLName("WorkflowActivity")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "WorkflowActivity")
public class WorkflowActivity implements Serializable {

	public static WorkflowActivity toDTO(String json) {
		return ObjectMapperUtil.readValue(WorkflowActivity.class, json);
	}

	public static WorkflowActivity unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(WorkflowActivity.class, json);
	}

	@Schema(
		description = "Type of workflow activity (NODE_ENTRY, TASK_ASSIGNMENT, etc.)"
	)
	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@JsonIgnore
	public void setActivityType(
		UnsafeSupplier<String, Exception> activityTypeUnsafeSupplier) {

		try {
			activityType = activityTypeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Type of workflow activity (NODE_ENTRY, TASK_ASSIGNMENT, etc.)"
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String activityType;

	@Schema(description = "Comment provided during the workflow activity")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@JsonIgnore
	public void setComment(
		UnsafeSupplier<String, Exception> commentUnsafeSupplier) {

		try {
			comment = commentUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Comment provided during the workflow activity")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String comment;

	@Schema(description = "When the action was performed")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public void setCreateDate(
		UnsafeSupplier<Date, Exception> createDateUnsafeSupplier) {

		try {
			createDate = createDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "When the action was performed")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date createDate;

	@Schema(description = "Name of the current workflow node")
	public String getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}

	@JsonIgnore
	public void setCurrentNode(
		UnsafeSupplier<String, Exception> currentNodeUnsafeSupplier) {

		try {
			currentNode = currentNodeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Name of the current workflow node")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String currentNode;

	@Schema(description = "Whether this is a terminal node in the workflow")
	public Boolean getIsTerminal() {
		return isTerminal;
	}

	public void setIsTerminal(Boolean isTerminal) {
		this.isTerminal = isTerminal;
	}

	@JsonIgnore
	public void setIsTerminal(
		UnsafeSupplier<Boolean, Exception> isTerminalUnsafeSupplier) {

		try {
			isTerminal = isTerminalUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Whether this is a terminal node in the workflow"
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean isTerminal;

	@Schema(description = "ID of the workflow log")
	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@JsonIgnore
	public void setLogId(UnsafeSupplier<Long, Exception> logIdUnsafeSupplier) {
		try {
			logId = logIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "ID of the workflow log")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long logId;

	@Schema(description = "Name of the previous workflow node")
	public String getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(String previousNode) {
		this.previousNode = previousNode;
	}

	@JsonIgnore
	public void setPreviousNode(
		UnsafeSupplier<String, Exception> previousNodeUnsafeSupplier) {

		try {
			previousNode = previousNodeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Name of the previous workflow node")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String previousNode;

	@Schema(description = "ID of the workflow task (if applicable)")
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@JsonIgnore
	public void setTaskId(
		UnsafeSupplier<Long, Exception> taskIdUnsafeSupplier) {

		try {
			taskId = taskIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "ID of the workflow task (if applicable)")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long taskId;

	@Schema(description = "ID of the user who performed the action")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@JsonIgnore
	public void setUserId(
		UnsafeSupplier<Long, Exception> userIdUnsafeSupplier) {

		try {
			userId = userIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "ID of the user who performed the action")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long userId;

	@Schema(description = "Full name of the user who performed the action")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public void setUserName(
		UnsafeSupplier<String, Exception> userNameUnsafeSupplier) {

		try {
			userName = userNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Full name of the user who performed the action"
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String userName;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof WorkflowActivity)) {
			return false;
		}

		WorkflowActivity workflowActivity = (WorkflowActivity)object;

		return Objects.equals(toString(), workflowActivity.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (activityType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"activityType\": ");

			sb.append("\"");

			sb.append(_escape(activityType));

			sb.append("\"");
		}

		if (comment != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"comment\": ");

			sb.append("\"");

			sb.append(_escape(comment));

			sb.append("\"");
		}

		if (createDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(createDate));

			sb.append("\"");
		}

		if (currentNode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"currentNode\": ");

			sb.append("\"");

			sb.append(_escape(currentNode));

			sb.append("\"");
		}

		if (isTerminal != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"isTerminal\": ");

			sb.append(isTerminal);
		}

		if (logId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"logId\": ");

			sb.append(logId);
		}

		if (previousNode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"previousNode\": ");

			sb.append("\"");

			sb.append(_escape(previousNode));

			sb.append("\"");
		}

		if (taskId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"taskId\": ");

			sb.append(taskId);
		}

		if (userId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userId\": ");

			sb.append(userId);
		}

		if (userName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userName\": ");

			sb.append("\"");

			sb.append(_escape(userName));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.ncema.dto.v1_0.WorkflowActivity",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
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
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

}