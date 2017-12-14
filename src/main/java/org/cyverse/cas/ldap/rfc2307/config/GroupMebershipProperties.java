package org.cyverse.cas.ldap.rfc2307.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cyverse.cas.ldap.rfc2307")
public class GroupMebershipProperties {

    /**
     * The base DN to use when searching for groups.
     */
    private String groupBaseDn;

    /**
     * The name of the LDAP attribute containing the group name.
     */
    private String groupNameAttribute = "cn";

    /**
     * The name of the LDAP attribute used to associate a group member with a group.
     */
    private String memberAttribute = "memberUid";

    /**
     * The name of the user attribute to store the list of group names.
     */
    private String groupsAttribute = "entitlement";

    /**
     * @return the base DN to use when searching for groups.
     */
    public String getGroupBaseDn() {
        return groupBaseDn;
    }

    /**
     * @param groupBaseDn the base DN to use when searching for groups.
     */
    public void setGroupBaseDn(String groupBaseDn) {
        this.groupBaseDn = groupBaseDn;
    }

    /**
     * @return the name of the LDAP attribute containing the group name.
     */
    public String getGroupNameAttribute() {
        return groupNameAttribute;
    }

    /**
     * @param groupNameAttribute the name of the LDAP attribute containing the group name.
     */
    public void setGroupNameAttribute(String groupNameAttribute) {
        this.groupNameAttribute = groupNameAttribute;
    }

    /**
     * @return the name of the LDAP attribute used to associate a group member with a group.
     */
    public String getMemberAttribute() {
        return memberAttribute;
    }

    /**
     * @param memberAttribute the name of the LDAP attribute used to associate a member with a group.
     */
    public void setMemberAttribute(String memberAttribute) {
        this.memberAttribute = memberAttribute;
    }

    /**
     * @return the name of the user attribute to store the list of group names.
     */
    public String getGroupsAttribute() {
        return groupsAttribute;
    }

    /**
     * @param groupsAttribute the name of the user attribute to store the list of group names.
     */
    public void setGroupsAttribute(String groupsAttribute) {
        this.groupsAttribute = groupsAttribute;
    }
}
