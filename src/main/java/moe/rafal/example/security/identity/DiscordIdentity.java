package moe.rafal.example.security.identity;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public record DiscordIdentity(long id,
							  @Nonnull String username,
							  @Nonnull String discriminator,
							  @Nonnull String email,
							  @Nullable String avatar) implements OAuth2User, Serializable {

	@Override
	public String getName() {
		return username;
	}

	@Override
	public Map<String, Object> getAttributes() {
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("id", id);
		attributes.put("username", username);
		attributes.put("discriminator", discriminator);
		attributes.put("email", email);
		attributes.put("avatar", avatar);
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}
}
