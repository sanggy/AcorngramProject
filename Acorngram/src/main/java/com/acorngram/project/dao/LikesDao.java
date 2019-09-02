package com.acorngram.project.dao;


import com.acorngram.project.dto.LikedDto;

public interface LikesDao {
	//liking a post
	public void likePost(LikedDto dto);
	
	//unliking a post
	public void unlikePost(LikedDto dto);
	
	//getlist of liked for a postnum
	public int getLikedPost(LikedDto dto);
	
	//post가 사라지면 같이 delete하는 메소드
	public int deleteAll(int post_num);
}
