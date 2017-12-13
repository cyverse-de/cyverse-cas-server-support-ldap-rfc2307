package org.cyverse.cas.ldap.rfc2307.config;

import org.apereo.cas.authentication.principal.resolvers.PersonDirectoryPrincipalResolver;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dennis
 */
@Configuration("ldapRfc2307PrincipalResolverConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class LdapPrincipalResolverConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(LdapPrincipalResolverConfiguration.class);

    @Autowired CasConfigurationProperties casProperties;

    @Bean
    public PersonDirectoryPrincipalResolver personDirectoryPrincipalResolver() {
        final PersonDirectoryPrincipalResolver resolver = new PersonDirectoryPrincipalResolver();
        return resolver;
    }
}
