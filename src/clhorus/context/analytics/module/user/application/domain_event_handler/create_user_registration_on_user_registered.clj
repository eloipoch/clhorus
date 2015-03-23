(ns clhorus.context.analytics.module.user.application.domain-event-handler.create-user-registration-on-user-registered
  (:use clhorus.context.analytics.module.user.application.service.user-registration-creator)
  (:require [vertx.eventbus :as eb]
            [clj-uuid :as uuid]))

(def address "domain-event")

(defn subscribe-create-user-registration-on-user-registered []
  (eb/on-message
    address
    (fn [domain-event]
      (print "Received message: ")
      (println domain-event)
      (create (uuid/as-uuid (:user-id domain-event)) (:occurred-on domain-event))
      ))
  )
