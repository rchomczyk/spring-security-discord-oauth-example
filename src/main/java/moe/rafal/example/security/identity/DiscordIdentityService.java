package moe.rafal.example.security.identity;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DiscordIdentityService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final DiscordIdentityMapperImpl identityMapper;
	private final RestTemplate restTemplate;

	@Autowired
	public DiscordIdentityService(DiscordIdentityMapperImpl identityMapper, RestTemplate restTemplate) {
		this.identityMapper = identityMapper;
		this.restTemplate = restTemplate;
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBearerAuth(userRequest.getAccessToken().getTokenValue());

		HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Map<String, Object>> response = exchangeAttributes(
			userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri(),
			entity);

		Map<String, Object> attributes = response.getBody();
		if (attributes != null) {
			return identityMapper.into(
				new DiscordIdentity()
					.id(Long.parseLong(attributes.get("id").toString()))
					.username(attributes.get("username").toString())
					.discriminator(attributes.get("discriminator").toString())
					.email(attributes.get("email").toString())
					.avatar(attributes.get("avatar").toString()));
		}

		return null;
	}

	private ResponseEntity<Map<String, Object>> exchangeAttributes(@Nonnull String uri, @Nonnull HttpEntity<?> entity) {
		return restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {

		});
	}
}
