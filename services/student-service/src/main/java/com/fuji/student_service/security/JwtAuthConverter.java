package com.fuji.student_service.security;

import com.nimbusds.jwt.JWTClaimNames;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtProperties properties;
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities= Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractRoles(jwt).stream()
        ).toList();

        return new JwtAuthenticationToken(jwt, authorities, getClaimName(jwt));
    }

    private String getClaimName(Jwt jwt) {
        String claimName = JWTClaimNames.SUBJECT;
        if (!Objects.isNull(properties.getPrincipalAttribute())) {
            claimName = jwt.getClaim(properties.getPrincipalAttribute());
        }
        return claimName;
    }
    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
        Map<String, Object> resource_access= jwt.getClaim("resource_access");
        Map<String, Object> resources;
        Collection<String> resources_roles;

        if ((Objects.isNull(resource_access))
                || (Objects.isNull(resources = (Map<String, Object>) resource_access.get(properties.getResourceId())))
                || (Objects.isNull(resources_roles= (Collection<String>) resources.get("roles")))) {

            return Set.of();
        }

        return resources_roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
