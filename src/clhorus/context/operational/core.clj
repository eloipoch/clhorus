(ns clhorus.context.operational.core
  (:require [clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler :as user-registration-command-handler]
            [clhorus.context.operational.infrastructure.registry :refer [command-bus-register]]
            [clhorus.context.operational.module.user.contract.command.user-registration-command])
  (:import (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))

(defn configure []
  (command-bus-register UserRegistrationCommand user-registration-command-handler/handle)
  )
