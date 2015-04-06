(ns clhorus.context.operational.module.user.application.service.user-registrator
  (:use clhorus.context.operational.module.user.domain.user.user)
  (:use clhorus.context.operational.module.user.domain.user.user-repository)
  ;(:require [clhorus.infrastructure.domain-event-publisher.vertx :refer [publish]])
  )

(defn register-user [user-id repository publisher]
  (let [[user domain-events] (create-user user-id)]
    (add repository user)
    (publisher domain-events)
    nil
    ))
