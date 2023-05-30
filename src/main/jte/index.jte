@import moe.rafal.example.security.identity.DiscordIdentity

@param DiscordIdentity identity

@template.structure.html-structure-up(title = "spring-security-discord-oauth-example")
<div class="app-content-child">
	<div class="greeting-card">
		<img class="greeting-subject-avatar"
				 src="https://cdn.discordapp.com/avatars/${identity.id()}/${identity.avatar()}.png?size=256"
				 alt="principal's avatar">
		<p>Hello <span class="greeting-subject">${identity.username()}</span></p>
	</div>
</div>
@template.structure.html-structure-dn()
