(ns clhorus.context.analytics.core
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.analytics.config :refer [analytics-config]]
            [clhorus.context.analytics.infrastructure.persistence.korma-component :refer [new-database]]
            [clhorus.context.analytics.module.user.infrastructure.worker.domain-event.worker :refer [new-domain-event-worker]]
            [clhorus.context.analytics.module.user.user-module-component :refer [new-user-module-system]]))

(defn new-context-analytics-system []
  (component/system-map
    :database-analytics (new-database (:database analytics-config))
    :domain-event-worker (-> (new-domain-event-worker)
                             (component/using [:domain-event-publisher]))
    :user-module (-> (new-user-module-system)
                     (component/using [:database-analytics
                                       :domain-event-publisher]))))
