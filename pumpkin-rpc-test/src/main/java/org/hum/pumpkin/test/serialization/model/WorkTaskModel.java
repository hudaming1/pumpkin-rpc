package org.hum.pumpkin.test.serialization.model;

import java.io.Serializable;

public class WorkTaskModel implements Serializable {
	private static final long serialVersionUID = 4379148696170975978L;
	private Integer workId;
	private String title;
	private String workContent;

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	@Override
	public String toString() {
		return "WorkTaskModel [workId=" + workId + ", title=" + title + ", workContent=" + workContent + "]";
	}
}
