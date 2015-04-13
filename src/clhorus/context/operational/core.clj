(ns clhorus.context.operational.core
  (:require
    [com.stuartsierra.component :as component]
    [clhorus.lib.command-bus.vertx]
    [clhorus.context.operational.infrastructure.command-bus.vertx]
    [clhorus.context.operational.infrastructure.persistence.korma.connection]
    [clhorus.context.operational.module.user.user-module-component :refer [user-module-system]])
  (:import (clhorus.context.operational.infrastructure.persistence.korma.connection DatabaseKormaComponent)
           (clhorus.lib.command_bus.vertx CommandBusVertx)))

(defn context-system [config-options]
  (component/system-map
    :database-operational (DatabaseKormaComponent. (:database config-options))
    :operational-command-bus (CommandBusVertx. (:command-bus-name config-options))
    :user-module (component/using (user-module-system) [:domain-event-publisher
                                                        :database-operational
                                                        :operational-command-bus])
    ))
