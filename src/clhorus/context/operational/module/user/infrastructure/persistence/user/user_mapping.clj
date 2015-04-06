(ns clhorus.context.operational.module.user.infrastructure.persistence.user.user-mapping
  (:use clhorus.context.operational.infrastructure.persistence.korma.connection)
  (:use clhorus.lib.uuid.uuid)
  (:use korma.core)
  (:require [clhorus.context.operational.module.user.domain.user.user])
  (:import (clhorus.context.operational.module.user.domain.user.user User)))

(def entity-user-korma
  (-> (create-entity "korma-user")
      (table :user)
      (entity-fields :id)))

;(defentity korma-user
;           (database operational-db)
;           (table :user)
;           (entity-fields :id)
;           )

(defn user-to-korma [^User user]
  {:id (-> user :user-id :id (uuid-to-binary))}
  )

; @todo korma-to-user
; @todo transform in the korma entity
