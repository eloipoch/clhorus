(ns clhorus.infrastructure.domain-event-publisher.vertx
  (:require [vertx.eventbus :as eb]
            [com.stuartsierra.component :as component]))

; @fixme subscribe that is listening to all messages
(defrecord DomainEventPublisherVertxComponent [address]
  component/Lifecycle

  (start [component]
    (-> component
        (assoc :publisher (partial eb/publish address))
        (assoc :subscribe (partial eb/on-message address)))
    )

  (stop [component]
    (-> component
        (assoc :publisher nil)
        (assoc :subscribe nil))
    ))
