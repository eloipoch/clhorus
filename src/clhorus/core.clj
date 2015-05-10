(ns clhorus.core
  (:require clhorus.app.api.handler
            [com.stuartsierra.component :as component]
            [clhorus.infrastructure.domain-event-publisher.channel-component]
            [clhorus.context.operational.core :refer [context-operational-system]]
            [clhorus.context.analytics.core :refer [context-analytics-system]]
            [clhorus.app.api.handler]
            )

  (:import (clhorus.infrastructure.domain_event_publisher.channel_component DomainEventPublisherChannelComponent)
           (clhorus.app.api.handler ApplicationApiComponent)))

(def main-config
  {:domain-event-address-name "domain-event"})

(def system
  (component/system-map
    :domain-event-publisher (DomainEventPublisherChannelComponent. (:domain-event-address-name main-config))
    :context-operational (component/using (context-operational-system) [:domain-event-publisher])
    :context-analytics (component/using (context-analytics-system) [:domain-event-publisher])
    :application-api (component/using (ApplicationApiComponent.) [:context-operational])
    ))

(defn start []
  (println "Starting...")
  (alter-var-root #'clhorus.core/system component/start))

(defn stop []
  (println "Stopping...")
  (alter-var-root #'clhorus.core/system component/stop))

(defn -main []
  (start))
