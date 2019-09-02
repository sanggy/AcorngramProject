package com.acorngram.project.dao;


import com.acorngram.project.dto.LikedDto;

public interface LikesDao {
	//liking a post
	public void likePost(LikedDto dto);
	
	//unliking a post
	public void unlikePost(LikedDto dto);
	
	//getlist of liked for a postnum
	public int getLikedPost(LikedDto dto);
}
