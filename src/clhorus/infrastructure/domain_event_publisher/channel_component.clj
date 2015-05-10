(ns clhorus.infrastructure.domain-event-publisher.channel-component
  (:require [com.stuartsierra.component :as component]))

; @fixme subscribe that is listening to all messages
; @fixme to implement
(defrecord DomainEventPublisherChannelComponent [address]
  component/Lifecycle

  (start [component]
    (-> component
        (assoc :publisher
               ;(partial eb/publish address)
               (partial println "publisher" address)
               )
        (assoc :subscribe
               ;(partial eb/on-message address)
               (partial println "subsribe" address)
               )))

  (stop [component]
    (-> component
        (assoc :publisher nil)
        (assoc :subscribe nil))))
