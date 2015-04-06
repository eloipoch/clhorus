(ns clhorus.context.operational.core
  (:require
    [com.stuartsierra.component :as component]
    [clhorus.context.operational.infrastructure.command-bus.vertx]
    [clhorus.context.operational.infrastructure.persistence.korma.connection]
    [clhorus.context.operational.module.user.infrastructure.persistence.user.user-mapping :refer [entity-user-korma]]
    [clhorus.context.operational.module.user.infrastructure.persistence.user.user_repository_mysql]
    [korma.core :as korma]
    [vertx.eventbus :as eb])
  (:import (clhorus.context.operational.infrastructure.command_bus.vertx CommandBusComponent)
           (clhorus.context.operational.infrastructure.persistence.korma.connection DatabaseKormaComponent)
           (clhorus.context.operational.module.user.infrastructure.persistence.user.user_repository_mysql UserRepositoryMySql)))

; @fixme move it
(def operational-config
  {:command-bus-name "operational-command-bus"
   :domain-event-address-name "domain-event"})

;; @fixme move it to main core
(defrecord DomainEventPublisherVertxComponent [address]
  component/Lifecycle

  (start [component]
    (assoc component :publisher (partial eb/publish address)))

  (stop [component]
    (assoc component :publisher nil)))

; @fixme move it
(defrecord EntityKormaComponent [entity]
  component/Lifecycle

  (start [component]
    (assoc component :entity (korma/database entity (:database (:database-operational component)))))

  (stop [component]
    (assoc component :entity nil)))

; @fixme move it
(defrecord UserRepositoryComponent []
  component/Lifecycle

  (start [component]
    (assoc component :repository (UserRepositoryMySql. (:entity (:entity-user component)))))

  (stop [component]
    (assoc component :repository nil)))


; @fixme create the UserModuleComponent
(defn operational-system [config-options]
  (component/system-map
    :domain-event-publisher (DomainEventPublisherVertxComponent. (:domain-event-address-name config-options))
    :database-operational (DatabaseKormaComponent. (:database config-options))
    :entity-user (component/using (EntityKormaComponent. entity-user-korma) {:database-operational :database-operational})
    :repository-user (component/using (UserRepositoryComponent.) {:entity-user :entity-user})
    :command-bus (component/using (CommandBusComponent. (:command-bus-name config-options)) {:repository-user :repository-user
                                                                                             :domain-event-publisher :domain-event-publisher})
    ))

(def system (component/start (operational-system operational-config)))

(defn configure []
  )
