(ns clhorus.context.operational.infrastructure.command-bus.vertx
  (:require [vertx.eventbus :as eb]))

(def address "operational-command-bus")


(defn- get-address [command-name]
  (str address "-" command-name)
  )

(defn- get-address-from-command [command]
  (get-address (:command command))
  )


(defn handle [command]
  (eb/send (get-address-from-command command) command)
  )

(defn register-command [command-name handle]
  (eb/on-message (get-address command-name) handle)
  )
