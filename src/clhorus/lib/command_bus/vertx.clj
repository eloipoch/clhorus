(ns clhorus.lib.command-bus.vertx
  (:require [vertx.eventbus :as eb]))

(defn- get-address [address command-name]
  (str address "-" command-name)
  )

(defn- get-address-from-command [address command]
  (get-address address (:command command))
  )


(defn handle [address command]
  (eb/send (get-address-from-command address command) command)
  )

(defn register-command [address command-name handle]
  (eb/on-message (get-address address command-name) handle)
  )
