package com.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.service.iMailService;

@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = "*")
public class MailController {

	@Autowired
	private iMailService mailService;

	@PostMapping("/")
	public void sendMail(@RequestBody String email) {
		mailService.sendMail(email);
	}

}
