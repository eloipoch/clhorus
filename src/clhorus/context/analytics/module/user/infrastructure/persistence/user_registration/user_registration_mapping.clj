(ns clhorus.context.analytics.module.user.infrastructure.persistence.user-registration.user-registration-mapping
  (:use clhorus.context.analytics.infrastructure.persistence.korma.connection)
  (:use clhorus.lib.uuid.uuid)
  (:import (clhorus.context.analytics.module.user.domain.user_registration.user_registration UserRegistration)))

(use 'korma.core)

(declare korma-user-registration)

(defentity korma-user-registration
           (database analytics-db)
           (table :user_registration)
           (entity-fields :user_id :registration_date)
           )

(defn entity-to-korma [^UserRegistration user-registration]
  {:user_id           (-> user-registration :user-id (uuid-to-binary))
   :registration_date (:registration-date user-registration)}
  )
