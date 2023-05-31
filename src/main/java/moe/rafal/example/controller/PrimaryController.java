package moe.rafal.example.controller;

import moe.rafal.example.security.identity.DiscordIdentity;
import moe.rafal.example.security.identity.DiscordIdentityMapperImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrimaryController {

	@GetMapping("/")
	public String renderHomeView(Model model, @AuthenticationPrincipal DiscordIdentity identity, DiscordIdentityMapperImpl identityMapper) {
		model.addAttribute("identity", identityMapper.from(identity));
		return "index";
	}
}
