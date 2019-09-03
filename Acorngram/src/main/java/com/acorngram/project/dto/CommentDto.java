package com.acorngram.project.dto;

public class CommentDto {
	private int num, usercode, target_code, ref_group, comment_group;
	private String content, deleted, regdate;
	private String nickname, id;
	
	public CommentDto() {}

	public CommentDto(int num, int usercode, int target_code, int ref_group, int comment_group, String content,
			String deleted, String regdate, String nickname, String id) {
		super();
		this.num = num;
		this.usercode = usercode;
		this.target_code = target_code;
		this.ref_group = ref_group;
		this.comment_group = comment_group;
		this.content = content;
		this.deleted = deleted;
		this.regdate = regdate;
		this.nickname = nickname;
		this.id = id;
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

	public int getTarget_code() {
		return target_code;
	}

	public void setTarget_code(int target_code) {
		this.target_code = target_code;
	}

	public int getRef_group() {
		return ref_group;
	}

	public void setRef_group(int ref_group) {
		this.ref_group = ref_group;
	}

	public int getComment_group() {
		return comment_group;
	}

	public void setComment_group(int comment_group) {
		this.comment_group = comment_group;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
