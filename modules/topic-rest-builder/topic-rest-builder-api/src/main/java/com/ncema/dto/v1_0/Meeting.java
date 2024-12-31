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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author root316
 * @generated
 */
@Generated("")
@GraphQLName(description = "Meeting object", value = "Meeting")
@JsonFilter("Liferay.Vulcan")
@Schema(
	description = "Meeting object",
	requiredProperties = {"meetingTitle", "meetingNumber", "meetingDateTime"}
)
@XmlRootElement(name = "Meeting")
public class Meeting implements Serializable {

	public static Meeting toDTO(String json) {
		return ObjectMapperUtil.readValue(Meeting.class, json);
	}

	public static Meeting unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Meeting.class, json);
	}

	@Schema(description = "Meeting ID")
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

	@GraphQLField(description = "Meeting ID")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long id;

	@Schema(description = "Meeting date and time")
	public Date getMeetingDateTime() {
		return meetingDateTime;
	}

	public void setMeetingDateTime(Date meetingDateTime) {
		this.meetingDateTime = meetingDateTime;
	}

	@JsonIgnore
	public void setMeetingDateTime(
		UnsafeSupplier<Date, Exception> meetingDateTimeUnsafeSupplier) {

		try {
			meetingDateTime = meetingDateTimeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Meeting date and time")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Date meetingDateTime;

	@Schema(description = "Meeting link")
	public String getMeetingLink() {
		return meetingLink;
	}

	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}

	@JsonIgnore
	public void setMeetingLink(
		UnsafeSupplier<String, Exception> meetingLinkUnsafeSupplier) {

		try {
			meetingLink = meetingLinkUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Meeting link")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String meetingLink;

	@Schema(description = "Meeting location")
	public String getMeetingLocation() {
		return meetingLocation;
	}

	public void setMeetingLocation(String meetingLocation) {
		this.meetingLocation = meetingLocation;
	}

	@JsonIgnore
	public void setMeetingLocation(
		UnsafeSupplier<String, Exception> meetingLocationUnsafeSupplier) {

		try {
			meetingLocation = meetingLocationUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Meeting location")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String meetingLocation;

	@Schema(description = "Meeting number")
	public Long getMeetingNumber() {
		return meetingNumber;
	}

	public void setMeetingNumber(Long meetingNumber) {
		this.meetingNumber = meetingNumber;
	}

	@JsonIgnore
	public void setMeetingNumber(
		UnsafeSupplier<Long, Exception> meetingNumberUnsafeSupplier) {

		try {
			meetingNumber = meetingNumberUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Meeting number")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Long meetingNumber;

	@Schema(description = "Meeting title")
	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	@JsonIgnore
	public void setMeetingTitle(
		UnsafeSupplier<String, Exception> meetingTitleUnsafeSupplier) {

		try {
			meetingTitle = meetingTitleUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Meeting title")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String meetingTitle;

	@Schema(description = "Array of participant user IDs")
	public Long[] getParticipants() {
		return participants;
	}

	public void setParticipants(Long[] participants) {
		this.participants = participants;
	}

	@JsonIgnore
	public void setParticipants(
		UnsafeSupplier<Long[], Exception> participantsUnsafeSupplier) {

		try {
			participants = participantsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Array of participant user IDs")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long[] participants;

	@Schema(description = "Array of topics or topic IDs")
	public Long[] getTopics() {
		return topics;
	}

	public void setTopics(Long[] topics) {
		this.topics = topics;
	}

	@JsonIgnore
	public void setTopics(
		UnsafeSupplier<Long[], Exception> topicsUnsafeSupplier) {

		try {
			topics = topicsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Array of topics or topic IDs")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long[] topics;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Meeting)) {
			return false;
		}

		Meeting meeting = (Meeting)object;

		return Objects.equals(toString(), meeting.toString());
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

		if (meetingDateTime != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingDateTime\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(meetingDateTime));

			sb.append("\"");
		}

		if (meetingLink != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingLink\": ");

			sb.append("\"");

			sb.append(_escape(meetingLink));

			sb.append("\"");
		}

		if (meetingLocation != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingLocation\": ");

			sb.append("\"");

			sb.append(_escape(meetingLocation));

			sb.append("\"");
		}

		if (meetingNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingNumber\": ");

			sb.append(meetingNumber);
		}

		if (meetingTitle != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meetingTitle\": ");

			sb.append("\"");

			sb.append(_escape(meetingTitle));

			sb.append("\"");
		}

		if (participants != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"participants\": ");

			sb.append("[");

			for (int i = 0; i < participants.length; i++) {
				sb.append(participants[i]);

				if ((i + 1) < participants.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (topics != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"topics\": ");

			sb.append("[");

			for (int i = 0; i < topics.length; i++) {
				sb.append(topics[i]);

				if ((i + 1) < topics.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.ncema.dto.v1_0.Meeting", name = "x-class-name"
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