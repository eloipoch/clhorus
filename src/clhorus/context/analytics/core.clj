(ns clhorus.context.analytics.core
  (:use clhorus.context.analytics.module.user.application.domain-event-handler.create-user-registration-on-user-registered)
  )

(defn configure []
  (subscribe-create-user-registration-on-user-registered)
  )
