(ns clhorus.context.operational.module.user.domain.user.user
  (:use clhorus.context.operational.module.user.domain.user.user-id)
  (:require [clj-time.local :as l])
  (:import (clhorus.context.operational.module.user.domain.user.user_id UserId)))

(defrecord User [^UserId user-id])

(defn create-user [user-id]
  [
   (User. user-id)
   {:domain-event-name "user-registered"
    :user-id           (.toString (:id user-id))
    :occurred-on       (l/format-local-time (l/local-now) :mysql) ; @fixme
    }
   ]
  )

