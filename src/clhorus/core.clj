(ns clhorus.core
  (:require clhorus.app.api.handler
            [com.stuartsierra.component :as component]
            [clhorus.context.operational.core :refer [new-context-operational-system]]
            [clhorus.context.analytics.core :refer [new-context-analytics-system]]
            [clhorus.app.api.handler]
            [clhorus.lib.domain-event.channel :refer [new-domain-event-publisher-channel]]
            )

  (:import (clhorus.app.api.handler ApplicationApiComponent)))

(defn clhorus-system []
  (component/system-map
    :domain-event-publisher (new-domain-event-publisher-channel)
    :context-operational (component/using (new-context-operational-system) [:domain-event-publisher])
    :context-analytics (component/using (new-context-analytics-system) [:domain-event-publisher])
    :application-api (component/using (ApplicationApiComponent.) [:context-operational])))

(def system (clhorus-system))

(defn start []
  (println "Starting...")
  (alter-var-root #'clhorus.core/system component/start))

(defn stop []
  (println "Stopping...")
  (alter-var-root #'clhorus.core/system component/stop))

(defn -main []
  (start))
