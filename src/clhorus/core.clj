(ns clhorus.core
  (:require clhorus.context.operational.core)
  (:require clhorus.context.analytics.core)
  (:require clhorus.app.api.handler)
  )

(defn- initialize-contexts []
  (clhorus.context.operational.core/configure)
  (clhorus.context.analytics.core/configure)
  )

(defn- initialize-applications []
  (clhorus.app.api.handler/run)
  )

(defn run []
  (initialize-contexts)
  (initialize-applications)
  )
