(defproject org.cyverse/cyverse-cas-server-support-ldap-rfc2307 "6.1.3-SNAPSHOT"
  :description "Plugin for the DataONE member node service."
  :license {:name "BSD"
            :url "http://www.cyverse.org/sites/default/files/iPLANT-LICENSE.txt"}
  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apereo.cas/cas-server-core-authentication "6.1.3" :exclusions [com.nimbusds/lang-tag]]
                 [org.apereo.cas/cas-server-core-configuration "6.1.3" :exclusions [com.nimbusds/lang-tag]]
                 [org.apereo.cas/cas-server-support-ldap "6.1.3" :exclusions [com.nimbusds/lang-tag]]
                 [org.projectlombok/lombok "1.18.10"]]
  :source-paths ["src/main/clojure"]
  :resource-paths ["src/main/resources"]
  :java-source-paths ["src/main/java"]
  :javac-options ["-target" "11" "-source" "11"]
  :aot :all)
