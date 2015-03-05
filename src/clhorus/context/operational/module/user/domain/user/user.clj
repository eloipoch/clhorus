(ns clhorus.context.operational.module.user.domain.user.user
  (:use clhorus.context.operational.module.user.domain.user.user-id))

(defrecord User [user-id])

(defn create-user [user-id]
  (User. (create-user-id user-id))
  )