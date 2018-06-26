(defproject org.cyverse/cyverse-cas-server-support-ldap-rfc2307 "1.0.1"
  :description "Plugin for the DataONE member node service."
  :license {:name "BSD"
            :url "http://www.cyverse.org/sites/default/files/iPLANT-LICENSE.txt"}
  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apereo.cas/cas-server-core-authentication "5.2.0" :exclusions [com.nimbusds/lang-tag]]
                 [org.apereo.cas/cas-server-core-configuration "5.2.0"]
                 [org.apereo.cas/cas-server-support-ldap "5.2.0" :exclusions [com.nimbusds/lang-tag]]]
  :source-paths ["src/main/clojure"]
  :resource-paths ["src/main/resources"]
  :java-source-paths ["src/main/java"]
  :javac-options ["-target" "1.8" "-source" "1.8"]
  :aot :all)
