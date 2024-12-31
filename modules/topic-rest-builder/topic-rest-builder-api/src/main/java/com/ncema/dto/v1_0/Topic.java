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

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author root316
 * @generated
 */
@Generated("")
@GraphQLName(description = "Topic object", value = "Topic")
@JsonFilter("Liferay.Vulcan")
@Schema(
	description = "Topic object",
	requiredProperties = {"topicTitle", "topicSubtitle", "topicDescription"}
)
@XmlRootElement(name = "Topic")
public class Topic implements Serializable {

	public static Topic toDTO(String json) {
		return ObjectMapperUtil.readValue(Topic.class, json);
	}

	public static Topic unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Topic.class, json);
	}

	@Schema(description = "Topic ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Topic ID")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long id;

	@Schema(description = "Dates of meetings this topic is included in")
	public Date[] getMeetingDates() {
		return meetingDates;
	}

	public void setMeetingDates(Date[] meetingDates) {
		this.meetingDates = meetingDates;
	}

	@JsonIgnore
	public void setMeetingDates(
		UnsafeSupplier<Date[], Exception> meetingDatesUnsafeSupplier) {

		try {
			meetingDates = meetingDatesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Dates of meetings this topic is included in")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date[] meetingDates;

	@Schema(description = "Number of meetings this topic is included in")
	public Integer getNumberOfMeetings() {
		return numberOfMeetings;
	}

	public void setNumberOfMeetings(Integer numberOfMeetings) {
		this.numberOfMeetings = numberOfMeetings;
	}

	@JsonIgnore
	public void setNumberOfMeetings(
		UnsafeSupplier<Integer, Exception> numberOfMeetingsUnsafeSupplier) {

		try {
			numberOfMeetings = numberOfMeetingsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Number of meetings this topic is included in")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer numberOfMeetings;

	@Schema(description = "Array of attachments for topic")
	@Valid
	public TopicAttachment[] getTopicAttachments() {
		return topicAttachments;
	}

	public void setTopicAttachments(TopicAttachment[] topicAttachments) {
		this.topicAttachments = topicAttachments;
	}

	@JsonIgnore
	public void setTopicAttachments(
		UnsafeSupplier<TopicAttachment[], Exception>
			topicAttachmentsUnsafeSupplier) {

		try {
			topicAttachments = topicAttachmentsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Array of attachments for topic")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected TopicAttachment[] topicAttachments;

	@Schema(description = "Topic description")
	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	@JsonIgnore
	public void setTopicDescription(
		UnsafeSupplier<String, Exception> topicDescriptionUnsafeSupplier) {

		try {
			topicDescription = topicDescriptionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Topic description")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String topicDescription;

	@Schema(description = "Topic status from picklist")
	public String getTopicStatus() {
		return topicStatus;
	}

	public void setTopicStatus(String topicStatus) {
		this.topicStatus = topicStatus;
	}

	@JsonIgnore
	public void setTopicStatus(
		UnsafeSupplier<String, Exception> topicStatusUnsafeSupplier) {

		try {
			topicStatus = topicStatusUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Topic status from picklist")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String topicStatus;

	@Schema(description = "Topic subtitle")
	public String getTopicSubtitle() {
		return topicSubtitle;
	}

	public void setTopicSubtitle(String topicSubtitle) {
		this.topicSubtitle = topicSubtitle;
	}

	@JsonIgnore
	public void setTopicSubtitle(
		UnsafeSupplier<String, Exception> topicSubtitleUnsafeSupplier) {

		try {
			topicSubtitle = topicSubtitleUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Topic subtitle")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String topicSubtitle;

	@Schema(description = "Topic title from picklist")
	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	@JsonIgnore
	public void setTopicTitle(
		UnsafeSupplier<String, Exception> topicTitleUnsafeSupplier) {

		try {
			topicTitle = topicTitleUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Topic title from picklist")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String topicTitle;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Topic)) {
			return false;
		}

		Topic topic = (Topic)object;

		return Objects.equals(toString(), topic.toString());
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

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		if (meetingDates != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingDates\": ");

			sb.append("[");

			for (int i = 0; i < meetingDates.length; i++) {
				sb.append("\"");

				sb.append(liferayToJSONDateFormat.format(meetingDates[i]));

				sb.append("\"");

				if ((i + 1) < meetingDates.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (numberOfMeetings != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"numberOfMeetings\": ");

			sb.append(numberOfMeetings);
		}

		if (topicAttachments != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicAttachments\": ");

			sb.append("[");

			for (int i = 0; i < topicAttachments.length; i++) {
				sb.append(String.valueOf(topicAttachments[i]));

				if ((i + 1) < topicAttachments.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (topicDescription != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicDescription\": ");

			sb.append("\"");

			sb.append(_escape(topicDescription));

			sb.append("\"");
		}

		if (topicStatus != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicStatus\": ");

			sb.append("\"");

			sb.append(_escape(topicStatus));

			sb.append("\"");
		}

		if (topicSubtitle != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicSubtitle\": ");

			sb.append("\"");

			sb.append(_escape(topicSubtitle));

			sb.append("\"");
		}

		if (topicTitle != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topicTitle\": ");

			sb.append("\"");

			sb.append(_escape(topicTitle));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.ncema.dto.v1_0.Topic", name = "x-class-name"
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