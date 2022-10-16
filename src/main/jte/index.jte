@import org.springframework.security.oauth2.core.user.OAuth2User

@param OAuth2User user

@template.schema.schema-up(title = "spring-security-discord-oauth-example")
<div class="greeting">
	<img class="greeting-subject-avatar"
			 src="https://cdn.discordapp.com/avatars/${(String) user.getAttribute("id")}/${(String) user.getAttribute("avatar")}.png?size=256"
			 alt="principal's avatar">
	<p>Hello <span class="greeting-subject">${user.getName()}</span></p>
</div>
<link rel="stylesheet" href="/public/index.css" type="text/css">
@template.schema.schema-dn()
