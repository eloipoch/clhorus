(defproject clhorus "0.0.1-SNAPSHOT"
            :description "Cool new project to do things and stuff"
            :dependencies [
                           [org.clojure/clojure "1.6.0"]
                           [compojure "1.3.1"]
                           [ring/ring-defaults "0.1.2"]
                           [korma "0.4.0"]
                           [mysql/mysql-connector-java "5.1.34"]
                           [danlentz/clj-uuid "0.1.2-SNAPSHOT"]
                           ]
            :plugins [[lein-ring "0.8.13"]]
            :ring {:open-browser? false
                   :handler       clhorus.app.api.handler/app}
            :profiles {:dev {:dependencies [
                                            [midje "1.6.3"]
                                            [lein-midje "3.1.3"]
                                            [javax.servlet/servlet-api "2.5"]
                                            [ring-mock "0.1.5"]
                                            ]
                             :plugins [[lein-midje "3.1.3"]]}}
            )
  
