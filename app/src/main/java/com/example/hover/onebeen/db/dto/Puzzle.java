package com.example.hover.onebeen.db.dto;

public class Puzzle {
	private long id;
	private String type;
	private String title;
	private String description;
	private String mediaUri;

	public Puzzle() {
	}

	public Puzzle(long id, String type, String title, String description, String mediaUri) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.description = description;
		this.mediaUri = mediaUri;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediaUri() {
		return mediaUri;
	}

	public void setMediaUri(String mediaUri) {
		this.mediaUri = mediaUri;
	}
}
