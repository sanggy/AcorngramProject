package com.acorngram.project.dto;

public class FollowerDto {
	private int self_usercode, target_usercode, status;
	
	public FollowerDto(){}

	public FollowerDto(int self_usercode, int target_usercode, int status) {
		super();
		this.self_usercode = self_usercode;
		this.target_usercode = target_usercode;
		this.status = status;
	}

	public int getSelf_usercode() {
		return self_usercode;
	}

	public void setSelf_usercode(int self_usercode) {
		this.self_usercode = self_usercode;
	}

	public int getTarget_usercode() {
		return target_usercode;
	}

	public void setTarget_usercode(int target_usercode) {
		this.target_usercode = target_usercode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
