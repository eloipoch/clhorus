(ns clhorus.lib.command-bus.channel
  (:require [clhorus.lib.command-bus.protocol]
            [clojure.core.async :as async :refer [>! go alts! chan]])
  (:import (clhorus.lib.command_bus.protocol CommandBus)))

; @todo this implementation is the same used for DomainEventPublisherChannel, so extract and refactor
(deftype CommandBusChannel [atom-chan-handlers]
  CommandBus

  (handle [this command]
    (let [handler-id (class command)
          handler    (get @atom-chan-handlers handler-id)]
      (if (nil? handler)
        (println "No handler found for " handler-id)
        (go (>! handler command)))))

  (register [this command-class handle]
    (let [handler-id command-class
          bus        (chan)]
      (->> bus
           (assoc @atom-chan-handlers handler-id)
           (reset! atom-chan-handlers))
      ; @fixme it should manage how ends the infinite loop
      (go (while true
            (let [[command channel] (alts! [bus])]
              (handle command))))
      handler-id))

  (unregister [this id]
    (->> (dissoc @atom-chan-handlers id)
         (reset! atom-chan-handlers))))

(defn new-command-bus-channel []
  (CommandBusChannel. (atom {})))
