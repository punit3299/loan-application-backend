package com.loan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loan.services.iMailService;

@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = "*")
public class MailController {

	@Autowired
	private iMailService mailService;

	@PostMapping
	public void sendMail(@RequestParam String email) {
		mailService.sendMail(email);
	}

}
