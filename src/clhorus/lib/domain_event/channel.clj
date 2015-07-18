(ns clhorus.lib.domain-event.channel
  (:require [clhorus.lib.domain-event.protocol]
            [clhorus.lib.bus.channel :refer [new-bus-channel]]
            [clhorus.lib.bus.protocol :as bus])
  (:import (clhorus.lib.domain_event.protocol DomainEventPublisher)))

(defrecord DomainEventPublisherChannel [bus-channel]
  DomainEventPublisher

  (publish [this domain-event]
    (bus/publish bus-channel domain-event))

  (subscribe [this domain-event-class handler]
    (bus/subscribe bus-channel domain-event-class handler))

  (unsubscribe [this id]
    (bus/unsubscribe bus-channel id)))

(defn new-domain-event-publisher-channel []
  (map->DomainEventPublisherChannel {:bus-channel (new-bus-channel)}))
