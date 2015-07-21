(ns clhorus.context.operational.core
  (:require
    [com.stuartsierra.component :as component]
    [clhorus.lib.command-bus.channel]
    [clhorus.context.operational.config :refer [operational-config]]
    [clhorus.context.operational.infrastructure.persistence.korma-component :refer [new-database]]
    [clhorus.context.operational.module.user.user-module-component :refer [new-user-module-system]]
    [clhorus.lib.command-bus.channel :refer [new-command-bus-channel]]))

(defn new-context-operational-system []
  (component/system-map
    :database-operational (new-database (:database operational-config))
    :operational-command-bus (new-command-bus-channel)
    :user-module (-> (new-user-module-system) (component/using [:domain-event-publisher
                                                                :database-operational
                                                                :operational-command-bus]))))
