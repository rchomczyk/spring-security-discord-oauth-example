package moe.rafal.example.security.identity;

import com.rchomczyk.example.model.DiscordIdentityDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DiscordIdentity extends DiscordIdentityDto implements OAuth2User {

	@Override
	public String getName() {
		return getUsername();
	}

	@Override
	public Map<String, Object> getAttributes() {
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("id", getId());
		attributes.put("username", getUsername());
		attributes.put("discriminator", getDiscriminator());
		attributes.put("email", getEmail());
		attributes.put("avatar", getAvatar());
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}
}
