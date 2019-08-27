package com.acorngram.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorngram.project.dao.UsersDao;

@Repository
public class UsersServiceImpl implements UsersService{
	@Autowired UsersDao dao;
}
