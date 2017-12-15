# RFC 2307 Authentication for CAS 5.2

## Motivation

CyVerse uses OpenLDAP with the schema defined by rfc2307. The built-in LDAP authentication works fine in
CAS, but we had some trouble getting LDAP group membership resolution to work. Most of the advice that we
found in the CAS mailing list suggested enabling the reverse group lookup in OpenLDAP. This feature is
only compatible with rfc2307bis, however.

We experimented with other ways to configure CAS to do the second group membership lookup to no avail.
There might be a way to get this to work without code, but if there is, we haven't found it yet. The
Person Directory principal resolver was one of the options that we considered, but the overhead of
developing a custom principal resolver was as much as the overhead of extending the existing LDAP support
to work with our LDAP schema.

## Caveats

This module is not intended to be used in configurations where more than one LDAP directory is used for
authentication. It may be possible to get it to work in such configurations, but they haven't been tested
by CyVerse.

## Usage

This module is intended to be (almost) a drop-in replacement for `cas-server-support-ldap`, which is
documented [here](https://apereo.github.io/cas/5.2.x/installation/LDAP-Authentication.html). To use this
module, first download the source and build it, then add it to the project description file for your CAS
overlay and configure it as you would normally configure `cas-server-support-ldap`. For example, you can
add the following dependency to your `pom.xml`:

```xml
        <dependency>
             <groupId>org.cyverse</groupId>
             <artifactId>cyverse-cas-server-support-ldap-rfc2307</artifactId>
             <version>1.0.0</version>
        </dependency>
```

There are four custom settings available for this module.

| Configuration Setting | Description |
| --------------------- | ----------- |
| cyverse.cas.ldap.rfc2307.groupBaseDn | The base DN to use for the group search (default: `*`) |
| cyverse.cas.ldap.rfc2307.groupNameAttribute | The LDAP attribute containing the group name (default: `cn`) |
| cyverse.cas.ldap.rfc2307.memberAttribute | The LDAP attribute used for listing group members (default: `memberUid`) |
| cyverse.cas.ldap.rfc2307.groupsAttribute | The CAS user attribute used to store the list of group names (default: `entitlement`) |

Setting the base DN for the group search is recommended. Specifying the other configuration settings may
be required depending on how your LDAP directory and CAS clients are configured.

### Example Configuration

```properties
cas.authn.ldap[0].useSsl=false
cas.authn.ldap[0].ldapUrl=ldap://ldap.example.org
cas.authn.ldap[0].baseDn=ou=People,dc=example,dc=org
cas.authn.ldap[0].dnFormat=uid=%s,ou=People,dc=example,dc=org
cas.authn.ldap[0].type=DIRECT
cas.authn.ldap[0].userFilter=uid={user}
cas.authn.ldap[0].principalAttributeId=uid

# Specify the base DN to use for group membership resolution.
cyverse.cas.ldap.rfc2307.groupBaseDn=ou=Groups,dc=example,dc=org

# Attributes to be included from LDAP, : indicates mapping to renamed value
# (e.g. sn:lastName -> {"lastName": "LastName", ...} )
cas.authn.ldap[0].principalAttributeList=sn:lastName,cn:name,givenName:firstName,mail:email

# Select the attributes to be released
cas.authn.attributeRepository.defaultAttributesToRelease=email,firstName,lastName,name,entitlement
cas.authn.attributeRepository.attributes.uid=uid
```

## License

http://www.cyverse.org/license
