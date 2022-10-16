package com.rchomczyk.example.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@GetMapping(value = "/session/identity", produces = MediaType.APPLICATION_JSON_VALUE)
	OAuth2User getIdentity(@AuthenticationPrincipal OAuth2User user) {
		return user;
	}
}
