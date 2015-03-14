(ns clhorus.context.analytics.module.user.application.domain-event-handler.create-user-registration-on-user-registered
  (:use clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler)
  (:require [vertx.eventbus :as eb]))

(def address "domain-event")

(defn subscribe-create-user-registration-on-user-registered []
  (eb/on-message
    address
    (fn [domain-event]
      (print "Received message: ")
      (println domain-event)
      ))
  )