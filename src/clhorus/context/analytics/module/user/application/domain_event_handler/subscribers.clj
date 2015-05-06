(ns clhorus.context.analytics.module.user.application.domain-event-handler.subscribers
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.analytics.module.user.application.domain-event-handler.create-user-registration-on-user-registered :refer [create-user-registration-on-user-registered]]))

; @fixme use a domain event deftype like command-bus and do the unsubscribe
(defrecord UserSubscribers []
  component/Lifecycle

  (start [component]
    (-> component
        (assoc :subscriber-handler-id-create-user-registration ((:subscribe (:domain-event-publisher component)) (partial create-user-registration-on-user-registered (:repository-user component)))))
    )

  (stop [component]
    (-> component
        (assoc :subscriber-handler-id-create-user-registration nil))
    )
  )
