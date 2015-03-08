(ns clhorus.context.operational.module.user.infrastructure.persistence.user.user-repository-mysql
  (:use clhorus.context.operational.module.user.infrastructure.persistence.user.user)
  (:use clhorus.context.operational.module.user.domain.user.user-repository))

(use 'korma.core)

(defrecord UserRepositoryMySql []
  UserRepository
  (add [this user]
    (insert korma-user
            (values {:id (-> user :user-id :id)})))
  )