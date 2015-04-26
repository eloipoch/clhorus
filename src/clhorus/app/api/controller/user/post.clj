(ns clhorus.app.api.controller.user.post
  (:require [clhorus.context.operational.module.user.contract.command.user-registration-command]
            [clhorus.lib.command-bus.protocol :as cb])
  (:import (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))

(defn users-post [operational-command-bus form-attributes-request]
  (let [user-id                   (:id form-attributes-request)
        user-registration-command (UserRegistrationCommand. user-id)]
    (cb/handle operational-command-bus user-registration-command)
    {:status 201}))
