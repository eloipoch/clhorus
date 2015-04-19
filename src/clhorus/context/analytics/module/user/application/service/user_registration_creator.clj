(ns clhorus.context.analytics.module.user.application.service.user-registration-creator
  (:use [clhorus.context.analytics.module.user.domain.user-registration.user-registration]
        [clhorus.context.analytics.module.user.domain.user-registration.user-registration-repository])
  (:import (clhorus.context.analytics.module.user.domain.user_registration.user_registration UserRegistration)))

(defn create [repository-user user-id registered-date]
  (add repository-user (UserRegistration. user-id registered-date)))
