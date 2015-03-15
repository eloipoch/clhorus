(ns clhorus.app.api.infrastructure.registry
  (:require clhorus.context.operational.infrastructure.command-bus.vertx)
  )

(def operational-command-bus-handle clhorus.context.operational.infrastructure.command-bus.vertx/handle)
