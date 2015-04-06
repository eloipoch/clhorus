(ns clhorus.context.operational.infrastructure.command-bus.vertx
  (:require [clhorus.lib.command-bus.vertx]
            [com.stuartsierra.component :as component]
            [clhorus.lib.command-bus.protocol :as command-bus]
            [clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler :as user-registration-command-handler])
  (:import (clhorus.lib.command_bus.vertx CommandBusVertx)
           (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))

(defrecord CommandBusComponent [name]
  component/Lifecycle

  (start [component]
    (let [command-bus (CommandBusVertx. name)
          user-registration-command-handler-id (command-bus/register command-bus UserRegistrationCommand (partial user-registration-command-handler/handle (:repository-user component) (:publisher (:domain-event-publisher component))))
          ]
      (-> component
          (assoc :command-bus-vertx command-bus)
          (assoc :user-registration-command-handler-id user-registration-command-handler-id)
          )))

  (stop [component]
    (command-bus/unregister (:command-bus-vertx component) (:user-registration-command-handler-id component))
    (-> component
        (assoc :command-bus-vertx nil)
        (assoc :user-registration-command-handler-id nil))))
