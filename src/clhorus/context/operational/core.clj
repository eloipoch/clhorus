(ns clhorus.context.operational.core
  (:require
    [com.stuartsierra.component :as component]
    [clhorus.context.operational.config :refer [operational-config]]
    [clhorus.context.operational.infrastructure.command-bus.vertx]
    [clhorus.context.operational.infrastructure.persistence.korma.connection]
    [clhorus.context.operational.module.user.infrastructure.persistence.user.user-mapping :refer [entity-user-korma]]
    [clhorus.context.operational.module.user.infrastructure.persistence.user.user-repository-mysql]
    [clhorus.infrastructure.domain-event-publisher.vertx])
  (:import (clhorus.context.operational.infrastructure.command_bus.vertx CommandBusComponent)
           (clhorus.context.operational.infrastructure.persistence.korma.connection DatabaseKormaComponent)
           (clhorus.context.operational.module.user.infrastructure.persistence.user.user_repository_mysql UserRepositoryMySql)
           (clhorus.infrastructure.domain_event_publisher.vertx DomainEventPublisherVertxComponent)))

; @fixme create the UserModuleComponent
(defn operational-system [config-options]
  (component/system-map
    :domain-event-publisher (DomainEventPublisherVertxComponent. (:domain-event-address-name config-options))
    :database-operational (DatabaseKormaComponent. (:database config-options))
    :repository-user (component/using (UserRepositoryMySql.) {:database :database-operational})
    :command-bus (component/using (CommandBusComponent. (:command-bus-name config-options)) {:repository-user        :repository-user
                                                                                             :domain-event-publisher :domain-event-publisher})
    ))

(def system (component/start (operational-system operational-config)))
