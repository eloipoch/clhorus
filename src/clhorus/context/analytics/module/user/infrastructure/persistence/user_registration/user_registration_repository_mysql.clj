(ns clhorus.context.analytics.module.user.infrastructure.persistence.user-registration.user-registration-repository-mysql
  (:use [clhorus.context.analytics.module.user.infrastructure.persistence.user-registration.user-registration-mapping]
        [clhorus.context.analytics.module.user.domain.user-registration.user-registration-repository])
  (:require [korma.core :as korma]))

; @todo find
(defrecord UserRegistrationRepositoryMySql []
  UserRegistrationRepository
  (add [component user]
    (let [entity-with-database (korma/database entity-user-registration-korma (:database (:database component)))]
      (korma/insert entity-with-database (korma/values (user-registration-to-korma user)))
      user))
  )

(defn new-user-registration-repository []
  (map->UserRegistrationRepositoryMySql {}))
