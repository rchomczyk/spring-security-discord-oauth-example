package moe.rafal.example.security.identity;

import com.rchomczyk.example.model.DiscordIdentityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscordIdentityMapper {

	DiscordIdentityDto from(DiscordIdentity origin);

	DiscordIdentity into(DiscordIdentityDto origin);
}
