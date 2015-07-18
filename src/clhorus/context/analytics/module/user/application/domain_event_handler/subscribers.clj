(ns clhorus.context.analytics.module.user.application.domain-event-handler.subscribers
  (:require [com.stuartsierra.component :as component]
            [clhorus.lib.domain-event.protocol :as domain-event]
            [clhorus.context.operational.module.user.contract.domain-event.user-registered-domain-event]
            [clhorus.context.analytics.module.user.application.domain-event-handler.create-user-registration-on-user-registered :refer [create-user-registration-on-user-registered]])
  (:import (clhorus.context.operational.module.user.contract.domain_event.user_registered_domain_event UserRegisteredDomainEvent)))

(defrecord UserSubscribers []
  component/Lifecycle

  (start [component]
    (let [publisher                            (:domain-event-publisher component)
          repository-user                      (:repository-user component)
          user-registration-command-handler-id (domain-event/subscribe publisher UserRegisteredDomainEvent (partial create-user-registration-on-user-registered repository-user))]
      (-> component
          (assoc :user-registered-domain-event-handler-id user-registration-command-handler-id))))

  (stop [component]
    (-> component
        (assoc :user-registered-domain-event-handler-id nil))
    )
  )

(defn new-user-subscribers []
  (map->UserSubscribers {}))
