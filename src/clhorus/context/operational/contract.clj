(ns clhorus.context.operational.contract
  (:require [clhorus.context.operational.infrastructure.command-bus.vertx :refer [handle]]))

(def command-bus-handle handle)
