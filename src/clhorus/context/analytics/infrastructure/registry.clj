(ns clhorus.context.analytics.infrastructure.registry
  (:require [clhorus.context.analytics.module.user.infrastructure.persistence.user-registration.user-registration-repository-mysql])
  (:import (clhorus.context.analytics.module.user.infrastructure.persistence.user_registration.user_registration_repository_mysql UserRegistrationRepositoryMySql)))

(def user-registration-repository (UserRegistrationRepositoryMySql.))
