(ns clhorus.context.operational.module.user.infrastructure.persistence.user.user-repository-mysql
  (:require [clhorus.context.operational.module.user.infrastructure.persistence.user.user-mapping :refer :all]
            [clhorus.context.operational.module.user.domain.user.user-repository]
            [korma.core :as korma])
  (:import (clhorus.context.operational.module.user.domain.user.user_repository UserRepository)))

(defrecord UserRepositoryMySql []
  UserRepository
  (add [component user]
    (let [entity-with-database (korma/database entity-user-korma (:database (:database component)))]
      (korma/insert entity-with-database (korma/values (user-to-korma user)))
      user))
  )
