(ns clhorus.context.operational.module.user.domain.user.user-id
  (:require [clj-uuid :as uuid]))

(defrecord UserId [id])

(defn create-user-id [id]
  {:pre [(uuid/uuid-string? id)]}
  (UserId. id)
  )