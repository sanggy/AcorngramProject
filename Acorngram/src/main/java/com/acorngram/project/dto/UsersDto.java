package com.acorngram.project.dto;

public class UsersDto {
	private String id, nickname, email, pw, regdate;
	private int usercode, reported;
	private boolean banned;
	
	public UsersDto(){}

	public UsersDto(String id, String nickname, String email, String pw, String regdate, int usercode, int reported,
			boolean banned) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.pw = pw;
		this.regdate = regdate;
		this.usercode = usercode;
		this.reported = reported;
		this.banned = banned;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getUsercode() {
		return usercode;
	}

	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}

	public int getReported() {
		return reported;
	}

	public void setReported(int reported) {
		this.reported = reported;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	
	
	
}
