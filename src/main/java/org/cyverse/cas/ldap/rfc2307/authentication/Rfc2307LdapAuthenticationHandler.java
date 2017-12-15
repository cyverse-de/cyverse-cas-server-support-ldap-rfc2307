package org.cyverse.cas.ldap.rfc2307.authentication;

import org.apereo.cas.authentication.LdapAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.authentication.support.LdapPasswordPolicyHandlingStrategy;
import org.apereo.cas.services.ServicesManager;
import org.cyverse.cas.ldap.rfc2307.config.Rfc2307LdapAuthenticationConfiguration;
import org.cyverse.cas.ldap.rfc2307.util.GroupMembershipResolver;
import org.ldaptive.LdapEntry;
import org.ldaptive.LdapException;
import org.ldaptive.auth.Authenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Rfc2307LdapAuthenticationHandler extends LdapAuthenticationHandler {
    private static final Logger LOG = LoggerFactory.getLogger(Rfc2307LdapAuthenticationConfiguration.class);

    /**
     * Used to resolve group membership information for a user.
     */
    private final GroupMembershipResolver groupMembershipResolver;

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
            Authenticator authenticator, LdapPasswordPolicyHandlingStrategy strategy,
            GroupMembershipResolver groupMembershipResolver) {
        super(name, servicesManager, principalFactory, order, authenticator, strategy);
        this.groupMembershipResolver = groupMembershipResolver;
    }

    @Override
    protected Map<String, Object> collectAttributesForLdapEntry(LdapEntry ldapEntry, String username) {
        Map<String, Object> attributes = super.collectAttributesForLdapEntry(ldapEntry, username);

        // Resolve the group membership information.
        try {
            attributes.put("entitlement", groupMembershipResolver.resolve(ldapEntry));
        } catch (LdapException e) {
            LOG.warn("error encountered during group membership resolution: {}", e.getMessage());
        }

        return attributes;
    }
}
