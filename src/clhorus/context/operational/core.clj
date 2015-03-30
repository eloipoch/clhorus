(ns clhorus.context.operational.core
  (:require
    [clhorus.context.operational.module.user.contract.command.user-registration-command]
    [com.stuartsierra.component :as component]
    [clhorus.context.operational.infrastructure.command-bus.vertx])
  (:import (clhorus.context.operational.infrastructure.command_bus.vertx CommandBusComponent)))

(def operational-config
  {:command-bus-name "operational-command-bus"})

(defn operational-system [config-options]
  (component/system-map
    :command-bus (-> (:command-bus-name config-options)
                     (CommandBusComponent.))
    ;:user-repository (UserRepositoryMySql.)
    ;:publish-domain-events publish
    ;:command-bus-handle handle
    ;:command-bus-register register
    ))

(def system (component/start (operational-system operational-config)))

(defn configure []
  )
