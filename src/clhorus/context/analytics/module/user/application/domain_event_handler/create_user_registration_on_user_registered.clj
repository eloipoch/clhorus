(ns clhorus.context.analytics.module.user.application.domain-event-handler.create-user-registration-on-user-registered
  (:require [vertx.eventbus :as eb]))

(def address "domain-event")

;(eb/on-message
;  address
;  (fn [domain-event]
;    (println "Received message" domain-event)
;    ))
