(ns clhorus.lib.command-bus.channel
  (:require [clhorus.lib.command-bus.protocol]
            [clojure.core.async :as async :refer :all])
  (:import (clhorus.lib.command_bus.protocol CommandBus)))

(deftype CommandBusChannel [atom-chan-handlers]
  CommandBus

  (handle [this command]
    (let [handler-id (class command)
          handler    (get @atom-chan-handlers handler-id)]
      (go (>! handler command))))

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
