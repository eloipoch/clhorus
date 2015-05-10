(ns clhorus.context.operational.core
  (:require
    [com.stuartsierra.component :as component]
    [clhorus.lib.command-bus.channel]
    [clhorus.context.operational.config :refer [operational-config]]
    [clhorus.context.operational.infrastructure.persistence.korma-component]
    [clhorus.context.operational.module.user.user-module-component :refer [user-module-system]])
  (:import (clhorus.lib.command_bus.channel CommandBusChannel)
           (clhorus.context.operational.infrastructure.persistence.korma_component DatabaseKormaComponent)))

(defn context-operational-system []
  (component/system-map
    :database-operational (DatabaseKormaComponent. (:database operational-config))
    :operational-command-bus (CommandBusChannel. (:command-bus-name operational-config))
    :user-module (component/using (user-module-system) [:domain-event-publisher
                                                        :database-operational
                                                        :operational-command-bus])
    ))
