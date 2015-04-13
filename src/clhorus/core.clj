(ns clhorus.core
  (:require clhorus.context.operational.core)
  (:require clhorus.context.analytics.core)
  (:require clhorus.app.api.handler
            [com.stuartsierra.component :as component]
            [clhorus.infrastructure.domain-event-publisher.vertx]
            [clhorus.context.operational.core :refer [context-system]]
            [clhorus.context.operational.config :refer [operational-config]])
  (:import (clhorus.infrastructure.domain_event_publisher.vertx DomainEventPublisherVertxComponent)))

(def main-config
  {:domain-event-address-name "domain-event"})

(defn main-system [config-options]
  (component/system-map
    :domain-event-publisher (DomainEventPublisherVertxComponent. (:domain-event-address-name config-options))
    :context-operational (component/using (context-system operational-config) [:domain-event-publisher])
    ))




(defn- initialize-contexts []
  (clhorus.context.analytics.core/configure)
  )

(defn- initialize-applications [system]
  (clhorus.app.api.handler/run system)
  )

(defn run []
  (let [system (component/start (main-system main-config))]
    (initialize-contexts)
    (initialize-applications system)
    )
  )
