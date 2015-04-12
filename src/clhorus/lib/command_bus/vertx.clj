(ns clhorus.lib.command-bus.vertx
  (:require [vertx.eventbus :as eb]
            [clhorus.lib.command-bus.protocol])
  (:import (clhorus.lib.command_bus.protocol CommandBus)))


(defn- get-address [address command-class]
  (str address "-" command-class))

(defn- get-address-from-command [address command]
  (get-address address (class command)))


(deftype CommandBusVertx [address]
  CommandBus

  (handle [this command]
    (println address)
    (println command)
    (eb/send (get-address-from-command address command) command))

  (register [this command-class handle]
    (eb/on-message (get-address address command-class) handle))

  (unregister [this id]
    (eb/unregister-handler id)
    ))
