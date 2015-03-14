(ns clhorus.infrastructure.domain-event-publisher.vertx
  (:require [vertx.eventbus :as eb]))

(def address "domain-event")

(defn publish [domain-events]
  ;(apply #(eb/publish address %) domain-events)
  (println "publish message: " domain-events)
  (eb/publish address domain-events)
  )
