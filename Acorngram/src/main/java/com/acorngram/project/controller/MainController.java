package com.acorngram.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.acorngram.project.service.UsersService;

@Controller
public class MainController {
	@Autowired UsersService users_service;
	
	
	
	
}
