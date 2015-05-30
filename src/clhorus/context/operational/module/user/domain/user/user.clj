(ns clhorus.context.operational.module.user.domain.user.user
  (:use clhorus.context.operational.module.user.domain.user.user-id)
  (:require [clj-time.local :as l]
            [clhorus.context.operational.module.user.contract.domain-event.user-registered-domain-event])
  (:import (clhorus.context.operational.module.user.domain.user.user_id UserId)
           (clhorus.context.operational.module.user.contract.domain_event.user_registered_domain_event UserRegisteredDomainEvent)))

(defrecord User [^UserId user-id])

(defn create-user [user-id]
  [
   (User. user-id)
   (UserRegisteredDomainEvent.
     (.toString (:id user-id))
     (l/format-local-time (l/local-now) :mysql) ; @fixme
     )
   ]
  )

