package com.acorngram.project.dto;

import org.springframework.web.multipart.MultipartFile;

public class PostDto {
	
	// usersname 
	private int num, usercode;
	private String content, image, video, regdate;
	private int like_count;
	// FileName, Path, Size 
	private String saveFileName;
	private long fileSize;
	
	//users_db와 post_db INNER JOIN한 SQL문에 추가된 정보
	private String id, nickname, profile_img;
	
	//페이징 처리를 위한 필드
	private int startRowNum;
	private int endRowNum;
	
	//likes db에서 가지고 온 정보
	private String liked;
	

	// <input type="file" name="file"/> 에서 name 속성의 value 와 동일한 필드명으로 
	// MultipartFile type 필드를 선언해야 한다.
	private MultipartFile file;
	
	
	public PostDto() {}


	public PostDto(int num, int usercode, String content, String image, String video, String regdate, int like_count,
			String saveFileName, long fileSize, String id, String nickname, String profile_img, int startRowNum, int endRowNum,
			String liked, MultipartFile file) {
		super();
		this.num = num;
		this.usercode = usercode;
		this.content = content;
		this.image = image;
		this.video = video;
		this.regdate = regdate;
		this.like_count = like_count;
		this.saveFileName = saveFileName;
		this.fileSize = fileSize;
		this.id = id;
		this.nickname = nickname;
		this.profile_img = profile_img;
		this.startRowNum = startRowNum;
		this.endRowNum = endRowNum;
		this.liked = liked;
		this.file = file;
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


	public int getLike_count() {
		return like_count;
	}


	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}


	public String getSaveFileName() {
		return saveFileName;
	}


	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}


	public long getFileSize() {
		return fileSize;
	}


	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
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


	public String getProfile_img() {
		return profile_img;
	}


	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}


	public int getStartRowNum() {
		return startRowNum;
	}


	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}


	public int getEndRowNum() {
		return endRowNum;
	}


	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
	
	
	
	public String getLiked() {
		return liked;
	}


	public void setLiked(String liked) {
		this.liked = liked;
	}


	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}	
	
}