(ns clhorus.context.analytics.module.user.application.service.user-registration-creator
  (:use [clhorus.context.analytics.module.user.domain.user-registration.user-registration]
        [clhorus.context.analytics.module.user.domain.user-registration.user-registration-repository]
        [clhorus.context.analytics.infrastructure.registry])
  (:import (clhorus.context.analytics.module.user.domain.user_registration.user_registration UserRegistration)))

(defn create [user-id registered-date]
  (add user-registration-repository (UserRegistration. user-id registered-date)))
