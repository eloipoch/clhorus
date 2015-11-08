(ns clhorus.context.analytics.core
  (:require [com.stuartsierra.component :as component]
            [clhorus.lib.rabbitmq.connection :refer [rabbitmq-new-connection]]
            [clhorus.context.analytics.config :refer [analytics-config]]
            [clhorus.context.analytics.infrastructure.persistence.korma-component :refer [new-database]]
            [clhorus.context.analytics.infrastructure.worker.worker :refer [new-domain-event-worker]]
            [clhorus.context.analytics.module.user.user-module-component :refer [new-user-module-system]]))

(defn new-context-analytics-system []
  (component/system-map
    :database-analytics (new-database (:database analytics-config))
    :rabbitmq-connection (rabbitmq-new-connection (:rabbitmq-connection analytics-config))
    :domain-event-worker (-> (new-domain-event-worker (get-in analytics-config [:rabbitmq-worker :exchange-name]))
                             (component/using [:rabbitmq-connection
                                               :domain-event-publisher]))
    :user-module (-> (new-user-module-system)
                     (component/using [:database-analytics
                                       :domain-event-publisher]))))
