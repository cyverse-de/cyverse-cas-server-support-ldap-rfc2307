(defproject org.cyverse/cyverse-cas-server-support-ldap-rfc2307 "5.3.16"
  :description "CAS support for RFC 2307 LDAP directories"
  :license {:name "BSD"
            :url "https://cyverse.org/license"}
  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]]
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.apereo.cas/cas-server-core-authentication "5.3.16" :exclusions [com.nimbusds/lang-tag]]
                 [org.apereo.cas/cas-server-core-configuration "5.3.16"]
                 [org.apereo.cas/cas-server-support-ldap "5.3.16" :exclusions [com.nimbusds/lang-tag]]]
  :source-paths ["src/main/clojure"]
  :resource-paths ["src/main/resources"]
  :java-source-paths ["src/main/java"]
  :javac-options ["-target" "1.8" "-source" "1.8"]
  :aot :all)
