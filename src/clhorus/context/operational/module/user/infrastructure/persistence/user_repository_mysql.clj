(ns clhorus.context.operational.module.user.infrastructure.persistence.user-repository-mysql
  (:use clhorus.context.operational.module.user.infrastructure.persistence.user-entity-korma)
  (:use clhorus.context.operational.module.user.domain.user.user-repository))

(use 'korma.core)

(defrecord UserRepositoryMySql []
  UserRepository
  (add [this user]
    (insert user-entity-korma
            (values {:id (-> user :user-id :id)}))
    )
  )