(ns clhorus.infrastructure.domain-event-publisher.vertx
  (:require [vertx.eventbus :as eb]
            [com.stuartsierra.component :as component]))

;(def address "domain-event")
;
;(defn publish [domain-events]
;  ;(apply #(eb/publish address %) domain-events)
;  (println "publish message: " domain-events)
;  (eb/publish address domain-events)
;  )

; @fixme move it to main core component
(defrecord DomainEventPublisherVertxComponent [address]
  component/Lifecycle

  (start [component]
    (assoc component :publisher (partial eb/publish address)))

  (stop [component]
    (assoc component :publisher nil)))
