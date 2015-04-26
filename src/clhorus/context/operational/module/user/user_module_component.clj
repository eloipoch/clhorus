(ns clhorus.context.operational.module.user.user-module-component
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.operational.module.user.infrastructure.persistence.user.user-repository-mysql]
            [clhorus.context.operational.module.user.infrastructure.command-bus.handlers-component])
  (:import (clhorus.context.operational.module.user.infrastructure.persistence.user.user_repository_mysql UserRepositoryMySql)
           (clhorus.context.operational.module.user.infrastructure.command_bus.handlers_component CommandBusHandlersComponent)))

(defn user-module-system []
  (component/system-map
    :repository-user (component/using (UserRepositoryMySql.) {:database :database-operational})
    :command-bus-operational-user-handlers (component/using (CommandBusHandlersComponent.) [:operational-command-bus
                                                                                            :repository-user
                                                                                            :domain-event-publisher])))
