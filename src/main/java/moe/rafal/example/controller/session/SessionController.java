package moe.rafal.example.controller.session;

import com.rchomczyk.example.api.SessionApi;
import com.rchomczyk.example.model.DiscordIdentityDto;
import moe.rafal.example.security.WithAuthenticationSupport;
import moe.rafal.example.security.identity.DiscordIdentityMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SessionController implements SessionApi, WithAuthenticationSupport {

	private final DiscordIdentityMapperImpl identityMapper;

	@Autowired
	public SessionController(DiscordIdentityMapperImpl identityMapper) {
		this.identityMapper = identityMapper;
	}

	@Override
	public ResponseEntity<DiscordIdentityDto> getIdentity() {
		return Optional.ofNullable(resolveIdentityFromContext())
			.map(identityMapper::from)
			.map(ResponseEntity::ok)
			.orElseGet(ResponseEntity.noContent()::build);
	}
}
