(ns clhorus.context.operational.infrastructure.command-bus.vertx
  (:require [clhorus.lib.command-bus.protocol :as cb]
            [clhorus.lib.command-bus.vertx])
  (:import (clhorus.lib.command_bus.vertx CommandBusVertx)))

(def command-bus (CommandBusVertx. "operational-command-bus"))

(defn handle [command]
  (cb/handle command-bus command))

(defn register [command-name handle]
  (cb/register command-bus command-name handle))
