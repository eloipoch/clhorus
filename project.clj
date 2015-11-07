(defproject clhorus "0.0.1-SNAPSHOT"
            :description "Clhorus experimentation project"
            :dependencies [[org.clojure/clojure "1.7.0"]
                           [compojure "1.3.3"]
                           [http-kit "2.1.18"]
                           [ring/ring-defaults "0.1.2"]
                           [korma "0.4.2"]
                           [mysql/mysql-connector-java "5.1.34"]
                           [danlentz/clj-uuid "0.1.2-SNAPSHOT"]
                           [clj-time "0.9.0"]
                           [com.stuartsierra/component "0.2.3"]
                           [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                           [com.novemberain/langohr "3.4.1"]]
            :plugins [[lein-ring "0.8.13"]]
            :profiles {:dev {:source-paths ["dev"]
                             :dependencies [[midje "1.6.3"]
                                            [lein-midje "3.1.3"]
                                            [javax.servlet/servlet-api "2.5"]
                                            [ring-mock "0.1.5"]
                                            [org.clojure/tools.namespace "0.2.10"]
                                            [org.clojure/java.classpath "0.2.2"]]
                             :plugins      [[lein-midje "3.1.3"]]}}
            )
  
