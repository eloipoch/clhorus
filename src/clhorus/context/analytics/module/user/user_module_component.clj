(ns clhorus.context.analytics.module.user.user-module-component
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.analytics.module.user.infrastructure.persistence.user-registration.user-registration-repository-mysql :refer [new-user-registration-repository]]
            [clhorus.context.analytics.module.user.application.domain-event-handler.subscribers :refer [new-user-subscribers]]))

(defn new-user-module-system []
  (component/system-map
    :repository-user (component/using (new-user-registration-repository) {:database :database-analytics})
    :domain-event-publisher-subscribers (component/using (new-user-subscribers) [:domain-event-publisher :repository-user])
    ))
