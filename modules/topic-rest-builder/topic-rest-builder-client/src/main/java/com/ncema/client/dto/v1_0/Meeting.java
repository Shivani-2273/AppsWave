package com.ncema.client.dto.v1_0;

import com.ncema.client.function.UnsafeSupplier;
import com.ncema.client.serdes.v1_0.MeetingSerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author root316
 * @generated
 */
@Generated("")
public class Meeting implements Cloneable, Serializable {

	public static Meeting toDTO(String json) {
		return MeetingSerDes.toDTO(json);
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

	public Date getMeetingDateTime() {
		return meetingDateTime;
	}

	public void setMeetingDateTime(Date meetingDateTime) {
		this.meetingDateTime = meetingDateTime;
	}

	public void setMeetingDateTime(
		UnsafeSupplier<Date, Exception> meetingDateTimeUnsafeSupplier) {

		try {
			meetingDateTime = meetingDateTimeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date meetingDateTime;

	public String getMeetingLink() {
		return meetingLink;
	}

	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}

	public void setMeetingLink(
		UnsafeSupplier<String, Exception> meetingLinkUnsafeSupplier) {

		try {
			meetingLink = meetingLinkUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String meetingLink;

	public String getMeetingLocation() {
		return meetingLocation;
	}

	public void setMeetingLocation(String meetingLocation) {
		this.meetingLocation = meetingLocation;
	}

	public void setMeetingLocation(
		UnsafeSupplier<String, Exception> meetingLocationUnsafeSupplier) {

		try {
			meetingLocation = meetingLocationUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String meetingLocation;

	public Long getMeetingNumber() {
		return meetingNumber;
	}

	public void setMeetingNumber(Long meetingNumber) {
		this.meetingNumber = meetingNumber;
	}

	public void setMeetingNumber(
		UnsafeSupplier<Long, Exception> meetingNumberUnsafeSupplier) {

		try {
			meetingNumber = meetingNumberUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long meetingNumber;

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public void setMeetingTitle(
		UnsafeSupplier<String, Exception> meetingTitleUnsafeSupplier) {

		try {
			meetingTitle = meetingTitleUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String meetingTitle;

	public Long[] getParticipants() {
		return participants;
	}

	public void setParticipants(Long[] participants) {
		this.participants = participants;
	}

	public void setParticipants(
		UnsafeSupplier<Long[], Exception> participantsUnsafeSupplier) {

		try {
			participants = participantsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long[] participants;

	public Long[] getTopics() {
		return topics;
	}

	public void setTopics(Long[] topics) {
		this.topics = topics;
	}

	public void setTopics(
		UnsafeSupplier<Long[], Exception> topicsUnsafeSupplier) {

		try {
			topics = topicsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long[] topics;

	@Override
	public Meeting clone() throws CloneNotSupportedException {
		return (Meeting)super.clone();
	}

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
		return MeetingSerDes.toJSON(this);
	}

}