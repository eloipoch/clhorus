(ns clhorus.context.operational.module.user.infrastructure.persistence.user.user-repository-mysql
  (:use clhorus.context.operational.module.user.infrastructure.persistence.user.user-mapping)
  (:use clhorus.context.operational.module.user.domain.user.user-repository))

(use 'korma.core)

; @todo find
(defrecord UserRepositoryMySql []
  UserRepository
  (add [this user]
    (insert korma-user (values (user-to-korma user))))
  )
