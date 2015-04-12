(ns clhorus.context.operational.core
  (:require
    [com.stuartsierra.component :as component]
    [clhorus.infrastructure.domain-event-publisher.vertx]
    [clhorus.lib.command-bus.vertx]
    [clhorus.context.operational.config :refer [operational-config]]
    [clhorus.context.operational.infrastructure.command-bus.vertx]
    [clhorus.context.operational.infrastructure.persistence.korma.connection]
    [clhorus.context.operational.module.user.user-module-component :refer [user-module-system]])
  (:import (clhorus.context.operational.infrastructure.persistence.korma.connection DatabaseKormaComponent)
           (clhorus.infrastructure.domain_event_publisher.vertx DomainEventPublisherVertxComponent)
           (clhorus.lib.command_bus.vertx CommandBusVertx)))

(defn operational-system [config-options]
  (component/system-map
    :domain-event-publisher (DomainEventPublisherVertxComponent. (:domain-event-address-name config-options))
    :database-operational (DatabaseKormaComponent. (:database config-options))
    :operational-command-bus (CommandBusVertx. (:command-bus-name config-options))
    :user-module (component/using (user-module-system) [:domain-event-publisher
                                                        :database-operational
                                                        :operational-command-bus])
    ))

(def system (component/start (operational-system operational-config)))
