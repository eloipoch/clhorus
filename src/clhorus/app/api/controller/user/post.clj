(ns clhorus.app.api.controller.user.post
  (:require [clhorus.context.operational.module.user.contract.command.user-registration-command]
            [clhorus.context.operational.core :as operational]
            [clhorus.lib.command-bus.protocol :as cb])
  (:import (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))

(defn users-post [{user-id :id}]
  (cb/handle (:command-bus-vertx (:command-bus operational/system)) (UserRegistrationCommand. user-id))
  {:status 201})
