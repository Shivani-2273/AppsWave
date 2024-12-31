package com.ncema.client.dto.v1_0;

import com.ncema.client.function.UnsafeSupplier;
import com.ncema.client.serdes.v1_0.TopicSerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author root316
 * @generated
 */
@Generated("")
public class Topic implements Cloneable, Serializable {

	public static Topic toDTO(String json) {
		return TopicSerDes.toDTO(json);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public Date[] getMeetingDates() {
		return meetingDates;
	}

	public void setMeetingDates(Date[] meetingDates) {
		this.meetingDates = meetingDates;
	}

	public void setMeetingDates(
		UnsafeSupplier<Date[], Exception> meetingDatesUnsafeSupplier) {

		try {
			meetingDates = meetingDatesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date[] meetingDates;

	public Integer getNumberOfMeetings() {
		return numberOfMeetings;
	}

	public void setNumberOfMeetings(Integer numberOfMeetings) {
		this.numberOfMeetings = numberOfMeetings;
	}

	public void setNumberOfMeetings(
		UnsafeSupplier<Integer, Exception> numberOfMeetingsUnsafeSupplier) {

		try {
			numberOfMeetings = numberOfMeetingsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer numberOfMeetings;

	public TopicAttachment[] getTopicAttachments() {
		return topicAttachments;
	}

	public void setTopicAttachments(TopicAttachment[] topicAttachments) {
		this.topicAttachments = topicAttachments;
	}

	public void setTopicAttachments(
		UnsafeSupplier<TopicAttachment[], Exception>
			topicAttachmentsUnsafeSupplier) {

		try {
			topicAttachments = topicAttachmentsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected TopicAttachment[] topicAttachments;

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public void setTopicDescription(
		UnsafeSupplier<String, Exception> topicDescriptionUnsafeSupplier) {

		try {
			topicDescription = topicDescriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String topicDescription;

	public String getTopicStatus() {
		return topicStatus;
	}

	public void setTopicStatus(String topicStatus) {
		this.topicStatus = topicStatus;
	}

	public void setTopicStatus(
		UnsafeSupplier<String, Exception> topicStatusUnsafeSupplier) {

		try {
			topicStatus = topicStatusUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String topicStatus;

	public String getTopicSubtitle() {
		return topicSubtitle;
	}

	public void setTopicSubtitle(String topicSubtitle) {
		this.topicSubtitle = topicSubtitle;
	}

	public void setTopicSubtitle(
		UnsafeSupplier<String, Exception> topicSubtitleUnsafeSupplier) {

		try {
			topicSubtitle = topicSubtitleUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String topicSubtitle;

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public void setTopicTitle(
		UnsafeSupplier<String, Exception> topicTitleUnsafeSupplier) {

		try {
			topicTitle = topicTitleUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String topicTitle;

	@Override
	public Topic clone() throws CloneNotSupportedException {
		return (Topic)super.clone();
	}

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
		return TopicSerDes.toJSON(this);
	}

}