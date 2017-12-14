package org.cyverse.cas.ldap.rfc2307.authentication;

import org.apereo.cas.authentication.LdapAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.authentication.support.LdapPasswordPolicyHandlingStrategy;
import org.apereo.cas.services.ServicesManager;
import org.ldaptive.auth.Authenticator;

public class Rfc2307LdapAuthenticationHandler extends LdapAuthenticationHandler {

    /**
     *  Creates a new authentication handler that delegates to the given authenticator.
     *
     * @param name the name of the authentication handler.
     * @param servicesManager the services manager.
     * @param principalFactory the principal factory.
     * @param order the sequential order of the authenticator.
     * @param authenticator the LDAP authenticator.
     * @param strategy the authentication strategy.
     */
    public Rfc2307LdapAuthenticationHandler(
            String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order,
            Authenticator authenticator, LdapPasswordPolicyHandlingStrategy strategy) {
        super(name, servicesManager, principalFactory, order, authenticator, strategy);
    }
}
