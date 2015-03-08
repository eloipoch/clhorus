(ns clhorus.context.operational.module.user.infrastructure.persistence.user.user
  (:use clhorus.context.operational.infrastructure.persistence.korma.connection)
  (:use clhorus.util.korma))

(use 'korma.core)

(declare korma-user)

(defentity korma-user
           (database operational-db)
           (table :user)
           (entity-fields :id)
           (prepare (translate-uuid-to-binary :id))
           )

