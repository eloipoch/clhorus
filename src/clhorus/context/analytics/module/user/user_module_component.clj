(ns clhorus.context.analytics.module.user.user-module-component
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.analytics.module.user.infrastructure.persistence.user-registration.user-registration-repository-mysql]
            [clhorus.context.analytics.module.user.application.domain-event-handler.subscribers])
  (:import (clhorus.context.analytics.module.user.application.domain_event_handler.subscribers UserSubscribers)
           (clhorus.context.analytics.module.user.infrastructure.persistence.user_registration.user_registration_repository_mysql UserRegistrationRepositoryMySql)))

(defn user-module-system []
  (component/system-map
    :repository-user (component/using (UserRegistrationRepositoryMySql.) {:database :database-analytics})
    :domain-event-publisher-subscribers (component/using (UserSubscribers.) [:domain-event-publisher :repository-user])
    ))
