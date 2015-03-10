(defproject clhorus "0.0.1-SNAPSHOT"
            :description "Clhorus experimentation project"
            :dependencies [
                           [org.clojure/clojure "1.6.0"]
                           [compojure "1.3.1"]
                           [ring/ring-defaults "0.1.2"]
                           [korma "0.4.0"]
                           [mysql/mysql-connector-java "5.1.34"]
                           [danlentz/clj-uuid "0.1.2-SNAPSHOT"]
                           [io.vertx/lang-clojure "1.0.4"]
                           ]
            :plugins [
                      [lein-ring "0.8.13"]
                      [lein-vertx "0.3.1"]
                      ]
            :profiles {:dev {:dependencies [
                                            [midje "1.6.3"]
                                            [lein-midje "3.1.3"]
                                            [javax.servlet/servlet-api "2.5"]
                                            [ring-mock "0.1.5"]
                                            ]
                             :plugins      [[lein-midje "3.1.3"]]}}
            :ring {:open-browser? false
                   :handler       clhorus.app.api.handler/app}
            :vertx {:main clhorus.app.vertx.init/init
                    :author "Jordi Llonch"
                    :keywords ["clhorus ddd clojure"]
                    :developers []}
            )
  
