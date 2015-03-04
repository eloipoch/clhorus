(ns clhorus.context.operational.module.user.application.service.user-registrator
  (:use clhorus.context.operational.module.user.infrastructure.persistence.user-repository-mysql)
  (:use clhorus.context.operational.module.user.domain.user.user)
  (:use clhorus.context.operational.module.user.domain.user.user-id)
  (:use clhorus.context.operational.infrastructure.registry)
  (:import (clhorus.context.operational.module.user.domain.user.user User)
           (clhorus.context.operational.module.user.domain.user.user_id UserId)))

(defn register-user [user-id]
  (.add-user user-repository (User. (UserId. user-id)))
  )