(ns clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler
  (:use clhorus.context.operational.module.user.application.service.user-registrator)
  (:use clhorus.context.operational.module.user.domain.user.user-id)
)

; @todo use record for the command
(def command-name :user-registration-command)

(defn handle [{user-id :user-id}]
  (-> (create-user-id user-id)
      (register-user))
  nil
  )
