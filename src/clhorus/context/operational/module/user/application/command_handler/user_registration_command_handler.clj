(ns clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler
  (:use clhorus.context.operational.module.user.application.service.user-registrator)
  (:use clhorus.context.operational.module.user.domain.user.user-id)
  (:require clhorus.context.operational.module.user.contract.command.user-registration-command)
  (:import (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))

(defn handle [^UserRegistrationCommand command]
  (-> (:user-id command)
      (create-user-id)
      (register-user))
  nil
  )
