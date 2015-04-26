(ns clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler
  (:require [clhorus.context.operational.module.user.contract.command.user-registration-command]
            [clhorus.context.operational.module.user.application.service.user-registrator :refer [register-user]]
            [clhorus.context.operational.module.user.domain.user.user-id :refer [create-user-id]]))

(defn handle [repository publisher command]
  (-> (:user-id command)
      (create-user-id)
      (register-user repository publisher))
  nil
  )
