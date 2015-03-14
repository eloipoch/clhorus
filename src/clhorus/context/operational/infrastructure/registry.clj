(ns clhorus.context.operational.infrastructure.registry
  (:use clhorus.context.operational.module.user.infrastructure.persistence.user.user-repository-mysql)
  (:use clhorus.context.operational.infrastructure.event-bus.domain-event-publisher-vertx)
  (:import (clhorus.context.operational.module.user.infrastructure.persistence.user.user_repository_mysql UserRepositoryMySql)))

(def user-repository (UserRepositoryMySql.))

(def domain-event-publish publish)

