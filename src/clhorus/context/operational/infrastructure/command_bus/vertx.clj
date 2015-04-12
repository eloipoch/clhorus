(ns clhorus.context.operational.infrastructure.command-bus.vertx
  (:require [com.stuartsierra.component :as component]
            [clhorus.lib.command-bus.protocol :as command-bus]
            [clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler :as user-registration-command-handler])
  (:import (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))

(defrecord CommandBusComponent []
  component/Lifecycle

  (start [component]
    (println "start commandbushandler")
    (let [command-bus (:operational-command-bus component)
          user-registration-command-handler-id (command-bus/register command-bus UserRegistrationCommand (partial user-registration-command-handler/handle (:repository-user component) (:publisher (:domain-event-publisher component))))
          ]
      (println command-bus)
      (println user-registration-command-handler-id)
      (-> component
          (assoc :user-registration-command-handler-id user-registration-command-handler-id)
          )))

  (stop [component]
    (command-bus/unregister (:operational-command-bus component) (:user-registration-command-handler-id component))
    (-> component
        (assoc :operational-command-bus nil)
        (assoc :user-registration-command-handler-id nil))))
