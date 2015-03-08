(ns clhorus.context.operational.module.user.infrastructure.persistence.user.user-mapping
  (:use clhorus.context.operational.infrastructure.persistence.korma.connection)
  (:use clhorus.util.korma)
  (:import (clhorus.context.operational.module.user.domain.user.user User)))

(use 'korma.core)

(declare korma-user)

(defentity korma-user
           (database operational-db)
           (table :user)
           (entity-fields :id)
           (prepare (translate-uuid-to-binary :id))
           )

(defn user-to-korma [^User user]
  {:id (-> user :user-id :id)}
  )

; @todo korma-to-user
; @todo transform in the korma entity
