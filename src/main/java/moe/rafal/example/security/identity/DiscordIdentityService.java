package moe.rafal.example.security.identity;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class DiscordIdentityService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final RestTemplate restTemplate;

	public DiscordIdentityService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBearerAuth(userRequest.getAccessToken().getTokenValue());

		HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
			userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri(),
			HttpMethod.GET,
			entity,
			new ParameterizedTypeReference<>() {

			});

		Map<String, Object> attributes = response.getBody();
		if (attributes != null) {
			return new DiscordIdentity(
				Long.parseLong(attributes.get("id").toString()),
				attributes.get("username").toString(),
				attributes.get("discriminator").toString(),
				attributes.get("email").toString(),
				attributes.get("avatar").toString());
		}

		return null;
	}
}
