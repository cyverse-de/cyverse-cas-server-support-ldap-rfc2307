package org.cyverse.cas.ldap.rfc2307.util;

import org.apereo.cas.configuration.model.support.ldap.LdapAuthenticationProperties;
import org.apereo.cas.util.LdapUtils;
import org.cyverse.cas.ldap.rfc2307.config.GroupMembershipProperties;
import org.ldaptive.ConnectionFactory;
import org.ldaptive.LdapEntry;
import org.ldaptive.LdapException;
import org.ldaptive.Response;
import org.ldaptive.ResultCode;
import org.ldaptive.SearchExecutor;
import org.ldaptive.SearchFilter;
import org.ldaptive.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dennis
 */
public class GroupMembershipResolver {
    private static final Logger LOG = LoggerFactory.getLogger(GroupMembershipResolver.class);

    private final ConnectionFactory connectionFactory;

    private final String baseDn;

    private final String usernameAttribute;

    private final String groupNameAttribute;

    private final String memberAttribute;

    /**
     * @param connectionFactory the LDAP connection factory.
     * @param baseDn the base DN to use for the group search.
     * @param usernameAttribute the username attribute in the user's LDAP entry.
     * @param groupNameAttribute the group name attribute in the group's LDAP entry.
     * @param memberAttribute the member attribute in the group's LDAP entry.
     */
    private GroupMembershipResolver(
            final ConnectionFactory connectionFactory, final String baseDn,
            final String usernameAttribute, final String groupNameAttribute,
            final String memberAttribute) {
        this.connectionFactory = connectionFactory;
        this.baseDn = baseDn;
        this.usernameAttribute = usernameAttribute;
        this.groupNameAttribute = groupNameAttribute;
        this.memberAttribute = memberAttribute;
    }

    /**
     * Resolves group membership information for an LDAP entry corresponding to a user.
     *
     * @param entry the user's LDAP entry.
     * @return A list of group names.
     * @throws LdapException if the LDAP search fails.
     */
    public List<String> resolve(LdapEntry entry) throws LdapException {
        final List<String> groups = new ArrayList<>();

        // Get the user ID.
        String userId = LdapUtils.getString(entry, usernameAttribute);
        if (userId == null) {
            LOG.warn("no user ID found in LDAP entry: skipping group membership resolution");
            return groups;
        }

        // Perform the search.
        SearchExecutor executor = new SearchExecutor();
        executor.setBaseDn(baseDn);
        Response<SearchResult> response = executor.search(connectionFactory, buildSearchFilter(userId));
        if (response.getResultCode() != ResultCode.SUCCESS) {
            LOG.error("the group membership lookup failed: {}", response.getMessage());
            return groups;
        }

        // Extract the list of group names.
        for (LdapEntry groupEntry : response.getResult().getEntries()) {
            final String groupName = LdapUtils.getString(groupEntry, groupNameAttribute);
            if (groupName != null) {
                groups.add(groupName);
            }
        }

        LOG.debug("resolved groups for {}: {}", userId, groups);
        return groups;
    }

    /**
     * Creates an LDAP search filter for groups that the user belongs to.
     *
     * @param userId the user ID.
     * @return the search filter.
     */
    private SearchFilter buildSearchFilter(String userId) {
        final SearchFilter filter = new SearchFilter("(" + memberAttribute + "={user})");
        LOG.error("search filter expression: {}", "(" + memberAttribute + "={user})");
        filter.setParameter("user", userId);
        return filter;
    }

    /**
     * Creates a group membership resolver for a set of configuration values.
     *
     * @param l the LDAP authentication configuration - for LDAP connection settings and the user ID
     *          attribute.
     * @param p the group membership properties - for the group base DN, group name attribute, and the
     *          group member attribute.
     * @return the group membership resolver.
     */
    public static GroupMembershipResolver fromConfig(LdapAuthenticationProperties l, GroupMembershipProperties p) {
        LOG.error("Group membership properties: {}", p);
        final ConnectionFactory connectionFactory = LdapUtils.newLdaptiveConnectionFactory(l);
        return new GroupMembershipResolver(
                connectionFactory, p.getGroupBaseDn(), p.getGroupNameAttribute(),
                l.getPrincipalAttributeId(), p.getMemberAttribute());
    }
}
