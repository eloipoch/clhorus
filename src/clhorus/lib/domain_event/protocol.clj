(ns clhorus.lib.domain-event.protocol)

(defprotocol DomainEventPublisher
  (publish [this domain-event])
  (subscribe [this domain-event-name handle])
  (unsubscribe [this domain-event-name]))
