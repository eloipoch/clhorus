(ns clhorus.app.api.infrastructure.registry
  (:require [clhorus.context.operational.infrastructure.registry :as operational]))

(def operational-command-bus-handle operational/command-bus-handle)
