(ns clhorus.lib.command-bus.vertx
  (:require [vertx.eventbus :as eb]
            [clhorus.lib.command-bus.protocol])
  (:import (clhorus.lib.command_bus.protocol CommandBus)))


(defn- get-address [address command-name]
  (str address "-" command-name))

(defn- get-address-from-command [address command]
  (get-address address (:command command)))


(deftype CommandBusVertx [address]
  CommandBus

  (handle [this command]
    (eb/send (get-address-from-command address command) command))

  (register [this command-name handle]
    (eb/on-message (get-address address command-name) handle)))
