package com.ncema.client.dto.v1_0;

import com.ncema.client.function.UnsafeSupplier;
import com.ncema.client.serdes.v1_0.TopicAttachmentSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author root316
 * @generated
 */
@Generated("")
public class TopicAttachment implements Cloneable, Serializable {

	public static TopicAttachment toDTO(String json) {
		return TopicAttachmentSerDes.toDTO(json);
	}

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public void setAttachmentId(
		UnsafeSupplier<Long, Exception> attachmentIdUnsafeSupplier) {

		try {
			attachmentId = attachmentIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long attachmentId;

	public String getFileBase64() {
		return fileBase64;
	}

	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

	public void setFileBase64(
		UnsafeSupplier<String, Exception> fileBase64UnsafeSupplier) {

		try {
			fileBase64 = fileBase64UnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String fileBase64;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileName(
		UnsafeSupplier<String, Exception> fileNameUnsafeSupplier) {

		try {
			fileName = fileNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String fileName;

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public void setMimeType(
		UnsafeSupplier<String, Exception> mimeTypeUnsafeSupplier) {

		try {
			mimeType = mimeTypeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String mimeType;

	@Override
	public TopicAttachment clone() throws CloneNotSupportedException {
		return (TopicAttachment)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TopicAttachment)) {
			return false;
		}

		TopicAttachment topicAttachment = (TopicAttachment)object;

		return Objects.equals(toString(), topicAttachment.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return TopicAttachmentSerDes.toJSON(this);
	}

}