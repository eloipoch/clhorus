(ns clhorus.context.operational.infrastructure.registry
  (:require [clhorus.context.operational.infrastructure.command-bus.vertx :refer [register handle]]
            [clhorus.infrastructure.domain-event-publisher.vertx :refer [publish]]
            [clhorus.context.operational.module.user.infrastructure.persistence.user.user-repository-mysql])
  (:import (clhorus.context.operational.module.user.infrastructure.persistence.user.user_repository_mysql UserRepositoryMySql)))

(def user-repository (UserRepositoryMySql.))

(def publish-domain-events publish)

(def command-bus-handle handle)
(def command-bus-register register)
