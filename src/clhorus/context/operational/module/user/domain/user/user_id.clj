(ns clhorus.context.operational.module.user.domain.user.user-id
  (:require [clj-uuid :as uuid])
  (:import (java.util UUID)))

(defrecord UserId [^UUID id])

(defn create-user-id [user-id]
  (-> (uuid/as-uuid user-id)
      (UserId.))
  )
