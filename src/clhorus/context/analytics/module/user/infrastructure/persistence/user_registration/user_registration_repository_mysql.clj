(ns clhorus.context.analytics.module.user.infrastructure.persistence.user-registration.user-registration-repository-mysql
  (:use clhorus.context.analytics.module.user.infrastructure.persistence.user-registration.user-registration-mapping)
  (:use clhorus.context.analytics.module.user.domain.user-registration.user-registration-repository))

(use 'korma.core)

; @todo find
(defrecord UserRegistrationRepositoryMySql []
  UserRegistrationRepository
  (add [this user-registration]
    (insert korma-user-registration (values (entity-to-korma user-registration)))
    user-registration)
  )
