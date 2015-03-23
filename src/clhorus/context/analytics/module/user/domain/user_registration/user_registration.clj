(ns clhorus.context.analytics.module.user.domain.user-registration.user-registration
  (:use clhorus.context.operational.module.user.domain.user.user-id))

(defrecord UserRegistration [user-id registration-date])
