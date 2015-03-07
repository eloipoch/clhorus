(ns clhorus.context.operational.module.user.application.service.user-registrator
  (:use clhorus.context.operational.module.user.domain.user.user)
  (:use clhorus.context.operational.module.user.domain.user.user-repository)
  (:use clhorus.context.operational.infrastructure.registry)
  )

(defn register-user [user-id]
  (->> (create-user user-id)
       (add user-repository))
  )
