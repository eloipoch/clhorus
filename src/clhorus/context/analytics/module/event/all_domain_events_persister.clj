(ns clhorus.context.analytics.module.event.all-domain-events-persister
  (:require [clojure.java.jdbc :as j]
            [clojure.data.json :as json]
            [clhorus.context.analytics.config :refer [analytics-config]]))

(defn handler [_ _ _ ^bytes payload]
  (let [domain-event (json/read-str (String. payload "UTF-8") :key-fn keyword)]
    (j/insert! (:database-jdbc analytics-config) :domain_event_2
               {
                :aggregate_id (:aggregate_id domain-event)
                :name         (:name domain-event)
                :occurred_on  (:occurred-on domain-event)
                :data         payload})))

