(ns clhorus.core
  (:require clhorus.app.api.http-server
            [com.stuartsierra.component :as component]
            [clhorus.context.operational.core :refer [new-context-operational-system]]
            [clhorus.context.analytics.core :refer [new-context-analytics-system]]
            [clhorus.app.api.http-server :refer [new-application-api]]
            [clhorus.lib.domain-event.channel :refer [new-domain-event-publisher-channel]]))

(defn clhorus-system []
  (component/system-map
    :domain-event-publisher (new-domain-event-publisher-channel)
    :context-operational (-> (new-context-operational-system)
                             (component/using [:domain-event-publisher]))
    :context-analytics (-> (new-context-analytics-system)
                           (component/using [:domain-event-publisher]))
    :application-api (-> (new-application-api)
                         (component/using [:context-operational]))))

(def system (clhorus-system))

(defn start []
  (println "Starting...")
  (alter-var-root #'clhorus.core/system component/start))

(defn stop []
  (println "Stopping...")
  (alter-var-root #'clhorus.core/system component/stop))

(defn -main []
  (start))
