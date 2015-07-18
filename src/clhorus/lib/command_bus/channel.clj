(ns clhorus.lib.command-bus.channel
  (:require [clhorus.lib.command-bus.protocol]
            [clhorus.lib.bus.channel :refer [new-bus-channel]]
            [clhorus.lib.bus.protocol :as bus]
            [clojure.core.async :as async :refer [>! go alts! chan]])
  (:import (clhorus.lib.command_bus.protocol CommandBus)))

(defrecord CommandBusChannel [bus-channel]
  CommandBus

  (handle [this command]
    (bus/publish bus-channel command))

  (register [this command-class handler]
    (bus/subscribe bus-channel command-class handler))

  (unregister [this id]
    (bus/unsubscribe bus-channel id)))

(defn new-command-bus-channel []
  (map->CommandBusChannel {:bus-channel (new-bus-channel)}))
