(ns clhorus.context.operational.module.user.domain.user.user-id)

(defrecord UserId [id])

(defn create-user-id [id]
  (UserId. id)
  )