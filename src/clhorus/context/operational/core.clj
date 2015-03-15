(ns clhorus.context.operational.core
  (:require [clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler :as user-registration-command-handler]
            [clhorus.context.operational.infrastructure.registry :refer [command-bus-register]])
  )

(defn configure []
  (command-bus-register user-registration-command-handler/command-name user-registration-command-handler/handle)
  )
