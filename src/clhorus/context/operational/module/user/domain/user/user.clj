(ns clhorus.context.operational.module.user.domain.user.user
  (:use clhorus.context.operational.module.user.domain.user.user-id)
  (:import (clhorus.context.operational.module.user.domain.user.user_id UserId)))

(defrecord User [^UserId user-id])

(defn create-user [user-id]
  (User. user-id)
  )