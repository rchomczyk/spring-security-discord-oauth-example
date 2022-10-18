@import org.springframework.security.oauth2.core.user.OAuth2User

@param OAuth2User user

@template.structure.html-structure-up(title = "spring-security-discord-oauth-example")
<div class="app-content-child">
	<div class="greeting-card">
		<img class="greeting-subject-avatar"
				 src="https://cdn.discordapp.com/avatars/${(String) user.getAttribute("id")}/${(String) user.getAttribute("avatar")}.png?size=256"
				 alt="principal's avatar">
		<p>Hello <span class="greeting-subject">${user.getName()}</span></p>
	</div>
</div>
@template.structure.html-structure-dn()
