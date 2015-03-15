(ns clhorus.context.operational.infrastructure.command-bus.vertx
  (:require [clhorus.lib.command-bus.vertx :as cb]))

;@todo maybe use protocols?

(def address "operational-command-bus")

(defn handle [command]
  (cb/handle address command)
  )

(defn register-command [command-name handle]
  (cb/register-command address command-name handle)
  )
