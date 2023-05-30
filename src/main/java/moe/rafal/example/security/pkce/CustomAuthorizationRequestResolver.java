package moe.rafal.example.security.pkce;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.PkceParameterNames;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

	private final StringKeyGenerator secureKeyGenerator =
		new Base64StringKeyGenerator(Base64.getUrlEncoder().withoutPadding(), 96);
	private final OAuth2AuthorizationRequestResolver defaultResolver;

	public CustomAuthorizationRequestResolver(ClientRegistrationRepository repo, String authorizationRequestBaseUri) {
		defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(repo, authorizationRequestBaseUri);
	}

	private static String createHash(String value) throws NoSuchAlgorithmException {
		byte[] digest = MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8));
		return Base64.getUrlEncoder()
			.withoutPadding()
			.encodeToString(digest);
	}

	@Override
	public OAuth2AuthorizationRequest resolve(HttpServletRequest servletRequest) {
		return customizeAuthorizationRequest(defaultResolver.resolve(servletRequest));
	}

	@Override
	public OAuth2AuthorizationRequest resolve(HttpServletRequest servletRequest, String clientRegistrationId) {
		return customizeAuthorizationRequest(defaultResolver.resolve(servletRequest, clientRegistrationId));
	}

	private OAuth2AuthorizationRequest customizeAuthorizationRequest(OAuth2AuthorizationRequest request) {
		if (request == null) {
			return null;
		}

		Map<String, Object> attributes = new HashMap<>(request.getAttributes());
		Map<String, Object> additionalParameters = new HashMap<>(request.getAdditionalParameters());
		addPkceParameters(attributes, additionalParameters);
		return OAuth2AuthorizationRequest.from(request)
			.attributes(attributes)
			.additionalParameters(additionalParameters)
			.build();
	}

	private void addPkceParameters(Map<String, Object> attributes, Map<String, Object> additionalParameters) {
		String codeVerifier = this.secureKeyGenerator.generateKey();
		attributes.put(PkceParameterNames.CODE_VERIFIER, codeVerifier);
		try {
			String codeChallenge = createHash(codeVerifier);
			additionalParameters.put(PkceParameterNames.CODE_CHALLENGE, codeChallenge);
			additionalParameters.put(PkceParameterNames.CODE_CHALLENGE_METHOD, "S256");
		} catch (NoSuchAlgorithmException e) {
			additionalParameters.put(PkceParameterNames.CODE_CHALLENGE, codeVerifier);
		}
	}
}
