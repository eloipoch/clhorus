(ns clhorus.context.operational.infrastructure.registry
  (:use clhorus.context.operational.module.user.infrastructure.persistence.user-repository-mysql)
  (:import (clhorus.context.operational.module.user.infrastructure.persistence.user_repository_mysql UserRepositoryMySql)))

(def user-repository (UserRepositoryMySql.))