(ns clhorus.context.operational.module.user.infrastructure.persistence.user.user-mapping
  (:refer-clojure :exclude [update])
  (:use [clhorus.lib.uuid.uuid]
        [korma.core]
        [korma.db])
  (:require [clhorus.context.operational.module.user.domain.user.user])
  (:import (clhorus.context.operational.module.user.domain.user.user User)))

(defentity entity-user-korma
           (table :user)
           (entity-fields :id))

(defn user-to-korma [^User user]
  {:id (-> user :user-id :id (uuid-to-binary))})

; @todo korma-to-user
; @todo transform in the korma entity
