(ns clhorus.context.operational.module.user.infrastructure.command-bus.handlers-component
  (:require [com.stuartsierra.component :as component]
            [clhorus.lib.command-bus.protocol :as command-bus]
            [clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler :as user-registration-command-handler])
  (:import (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))

; @todo create an abstract way
(defrecord CommandBusHandlersComponent []
  component/Lifecycle

  (start [component]
    (let [command-bus                          (:operational-command-bus component)
          repository-user                      (:repository-user component)
          publisher                            (get-in component [:domain-event-publisher :publisher])
          user-registration-command-handler-id (command-bus/register command-bus UserRegistrationCommand (partial user-registration-command-handler/handle repository-user publisher))]
      (-> component
          (assoc :user-registration-command-handler-id user-registration-command-handler-id)
          )))

  (stop [component]
    (command-bus/unregister (:operational-command-bus component) (:user-registration-command-handler-id component))
    (-> component
        (assoc :operational-command-bus nil)
        (assoc :user-registration-command-handler-id nil))))
