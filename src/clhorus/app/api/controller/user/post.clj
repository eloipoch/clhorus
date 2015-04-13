(ns clhorus.app.api.controller.user.post
  (:require [clhorus.context.operational.module.user.contract.command.user-registration-command]
            [clhorus.lib.command-bus.protocol :as cb])
  (:import (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))

(defn users-post [system {user-id :id}]
  (cb/handle (:operational-command-bus (:context-operational system)) (UserRegistrationCommand. user-id))
  {:status 201})
