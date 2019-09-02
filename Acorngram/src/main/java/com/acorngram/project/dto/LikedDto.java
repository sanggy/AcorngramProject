package com.acorngram.project.dto;

public class LikedDto {
	private int user_code;
	private int post_num;
	
	public LikedDto() {}

	public LikedDto(int user_code, int post_num) {
		super();
		this.user_code = user_code;
		this.post_num = post_num;
	}

	public int getUser_code() {
		return user_code;
	}

	public void setUser_code(int user_code) {
		this.user_code = user_code;
	}

	public int getPost_num() {
		return post_num;
	}

	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	
}