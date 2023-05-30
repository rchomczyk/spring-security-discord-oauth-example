package moe.rafal.example.controller;

import moe.rafal.example.security.identity.DiscordIdentity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrimaryController {

	@GetMapping("/")
	public String renderHomeView(Model model, @AuthenticationPrincipal DiscordIdentity identity) {
		model.addAttribute("identity", identity);
		return "index";
	}
}
