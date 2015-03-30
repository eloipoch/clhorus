(ns clhorus.context.operational.module.user.application.service.user-registrator
  (:use clhorus.context.operational.module.user.domain.user.user)
  (:use clhorus.context.operational.module.user.domain.user.user-repository)
  (:require [clhorus.infrastructure.domain-event-publisher.vertx :refer [publish]]
            [clhorus.context.operational.module.user.infrastructure.persistence.user.user-repository-mysql])
  (:import (clhorus.context.operational.module.user.infrastructure.persistence.user.user_repository_mysql UserRepositoryMySql)))

(def user-repository (UserRepositoryMySql.))

(def publish-domain-events publish)

(defn register-user [user-id]
  (let [[user domain-events] (create-user user-id)]
    (add user-repository user)
    (publish-domain-events domain-events)
    nil
    ))
