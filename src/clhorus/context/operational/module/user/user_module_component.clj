(ns clhorus.context.operational.module.user.user-module-component
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.operational.module.user.infrastructure.persistence.user.user-repository-mysql :refer [new-user-repository]]
            [clhorus.context.operational.module.user.infrastructure.command-bus.handlers-component :refer [new-command-bus-handler]]))

(defn new-user-module-system []
  (component/system-map
    :repository-user (-> (new-user-repository) (component/using {:database :database-operational}))
    :command-bus-operational-user-handlers (-> (new-command-bus-handler) (component/using [:operational-command-bus
                                                                                           :repository-user
                                                                                           :domain-event-publisher]))))
