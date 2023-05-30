package moe.rafal.example.controller;

import moe.rafal.example.security.identity.DiscordIdentity;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@GetMapping(value = "/session/identity", produces = MediaType.APPLICATION_JSON_VALUE)
	public DiscordIdentity getIdentity(@AuthenticationPrincipal DiscordIdentity identity) {
		return identity;
	}
}
