package com.acorngram.project.dto;

public class PostDto {
	private String name, id, content, image, video, regdate;
	int num, usercode, like_count;
	
	
	public PostDto() {}


	public PostDto(String name, String id, String content, String image, String video, String regdate, int num, int usercode, int like_count) {
		super();
		this.name = name;
		this.id = id;
		this.content = content;
		this.image = image;
		this.video = video;
		this.regdate = regdate;
		this.num = num;
		this.usercode = usercode;
		this.like_count = like_count;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public int getUsercode() {
		return usercode;
	}


	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}


	public int getLike_count() {
		return like_count;
	}


	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
}
