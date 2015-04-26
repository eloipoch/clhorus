(ns clhorus.core
  (:require clhorus.context.operational.core)
  (:require clhorus.context.analytics.core)
  (:require clhorus.app.api.handler
            [com.stuartsierra.component :as component]
            [clhorus.infrastructure.domain-event-publisher.vertx-component]
            [clhorus.context.operational.core :refer [context-operational-system]]
            [clhorus.context.analytics.core :refer [context-analytics-system]]
            )

  (:import (clhorus.infrastructure.domain_event_publisher.vertx_component DomainEventPublisherVertxComponent)))

(def main-config
  {:domain-event-address-name "domain-event"})

(defn main-system [config-options]
  (component/system-map
    :domain-event-publisher (DomainEventPublisherVertxComponent. (:domain-event-address-name config-options))
    :context-operational (component/using (context-operational-system) [:domain-event-publisher])
    :context-analytics (component/using (context-analytics-system) [:domain-event-publisher])
    ))

(defn- initialize-applications [system]
  (clhorus.app.api.handler/run (:context-operational system))
  )

(defn run []
  (let [system (component/start (main-system main-config))]
    (initialize-applications system)
    )
  )
