package moe.rafal.example.security;

import moe.rafal.example.security.identity.DiscordIdentity;
import org.springframework.security.core.context.SecurityContextHolder;

public interface WithAuthenticationSupport {

	default DiscordIdentity resolveIdentityFromContext() {
		return (DiscordIdentity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
