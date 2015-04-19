(ns clhorus.context.analytics.module.user.infrastructure.persistence.user-registration.user-registration-mapping
  (:use [clhorus.context.analytics.infrastructure.persistence.korma.connection]
        [clhorus.lib.uuid.uuid]
        [korma.core])
  (:require [clhorus.context.analytics.module.user.domain.user-registration.user-registration])
  (:import (clhorus.context.analytics.module.user.domain.user_registration.user_registration UserRegistration)))

(defentity entity-user-registration-korma
           (table :user_registration)
           (entity-fields :user_id :registration_date))

(defn user-registration-to-korma [^UserRegistration user-registration]
  {:user_id           (-> user-registration :user-id (uuid-to-binary))
   :registration_date (:registration-date user-registration)})
