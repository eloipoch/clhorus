(ns clhorus.lib.domain-event.channel
  (:require [clhorus.lib.domain-event.protocol]
            [clojure.core.async :as async :refer [>! go alts! chan]])
  (:import (clhorus.lib.domain_event.protocol DomainEventPublisher)))

(deftype DomainEventPublisherChannel [atom-chan-handlers]
  DomainEventPublisher

  (publish [this domain-event]
    (let [handler-id (class domain-event)
          handler    (get @atom-chan-handlers handler-id)]
      (go (>! handler domain-event))))

  (subscribe [this domain-event-class handler]
    (let [handler-id domain-event-class
          bus        (chan)]
      (->> bus
           (assoc @atom-chan-handlers handler-id)
           (reset! atom-chan-handlers))
      ; @fixme it should manage how ends the infinite loop
      (go (while true
            (let [[domain-event channel] (alts! [bus])]
              (handler domain-event))))
      handler-id))

  (unsubscribe [this id]
    (->> (dissoc @atom-chan-handlers id)
         (reset! atom-chan-handlers))))

(defn new-domain-event-publisher-channel []
  (DomainEventPublisherChannel. (atom {})))
