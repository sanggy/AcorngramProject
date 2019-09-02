package com.acorngram.project.dto;

import org.springframework.web.multipart.MultipartFile;

public class UsersDto {
	private String id, dob, theme, profile_img;
	private String nickname, email, pw, regdate;
	private int usercode, reported, dm_range;
	private String banned, acc_private;
	private MultipartFile profile_file;
	
	public UsersDto(){}

	public UsersDto(String id, String dob, String theme, String profile_img, String nickname, String email, String pw,
			String regdate, int usercode, int reported, int dm_range, String banned, String acc_private,
			MultipartFile profile_file) {
		super();
		this.id = id;
		this.dob = dob;
		this.theme = theme;
		this.profile_img = profile_img;
		this.nickname = nickname;
		this.email = email;
		this.pw = pw;
		this.regdate = regdate;
		this.usercode = usercode;
		this.reported = reported;
		this.dm_range = dm_range;
		this.banned = banned;
		this.acc_private = acc_private;
		this.profile_file = profile_file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
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

	public int getDm_range() {
		return dm_range;
	}

	public void setDm_range(int dm_range) {
		this.dm_range = dm_range;
	}

	public String getBanned() {
		return banned;
	}

	public void setBanned(String banned) {
		this.banned = banned;
	}

	public String getAcc_private() {
		return acc_private;
	}

	public void setAcc_private(String acc_private) {
		this.acc_private = acc_private;
	}

	public MultipartFile getProfile_file() {
		return profile_file;
	}

	public void setProfile_file(MultipartFile profile_file) {
		this.profile_file = profile_file;
	}

	
	
}
