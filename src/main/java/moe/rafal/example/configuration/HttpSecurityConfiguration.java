package moe.rafal.example.configuration;

import moe.rafal.example.security.identity.DiscordIdentityService;
import moe.rafal.example.security.pkce.CustomAuthorizationRequestResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import static org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;

@EnableWebSecurity
@Configuration
public class HttpSecurityConfiguration {

	private final ClientRegistrationRepository clientRegistrationRepository;

	@Autowired
	public HttpSecurityConfiguration(ClientRegistrationRepository clientRegistrationRepository) {
		this.clientRegistrationRepository = clientRegistrationRepository;
	}

	@Bean
	public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity, OAuth2UserService<OAuth2UserRequest, OAuth2User> discordIdentityService)
		throws Exception {
		return httpSecurity.authorizeHttpRequests(requests -> requests
				.requestMatchers(new AntPathRequestMatcher("/public/**"))
				.permitAll()
				.anyRequest()
				.authenticated())
			.oauth2Login(configurer -> configurer
				.defaultSuccessUrl("/")
				.authorizationEndpoint(customizer -> customizer.authorizationRequestResolver(new CustomAuthorizationRequestResolver(clientRegistrationRepository, DEFAULT_AUTHORIZATION_REQUEST_BASE_URI)))
				.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(discordIdentityService)))
			.logout(LogoutConfigurer::permitAll)
			.build();
	}

	@Bean
	public OAuth2UserService<OAuth2UserRequest, OAuth2User> configureDiscordIdentityService(RestTemplate restTemplate) {
		return new DiscordIdentityService(restTemplate);
	}
}
