(ns clhorus.context.analytics.module.event.all-domain-events-persister
  (:require [clojure.java.jdbc :as j]
            [clojure.data.json :as json]))

(def mysql-db {:subprotocol "mysql"
               :subname     "//127.0.0.1:3306/horus_analytics"
               :user        "root"
               :password    ""})

(defn handler [_ _ _ ^bytes payload]
  (println "persist event to mysql")
  (let [domain-event (json/read-str (String. payload "UTF-8") :key-fn keyword)]
    (j/insert! mysql-db :domain_event_2
               {
                :aggregate_id (:aggregate_id domain-event)
                :name         (:name domain-event)
                :occurred_on  (:occurred-on domain-event)
                :data         payload})))

