(ns clhorus.context.analytics.infrastructure.worker.worker
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.analytics.config :refer [analytics-config]]
            [clhorus.lib.rabbitmq.connection :refer [rabbitmq-new-connection]]
            [clhorus.lib.rabbitmq.worker :refer [rabbitmq-new-worker]]
            [clhorus.context.analytics.module.event.all-domain-events-persister :as all-domain-events-persister]))

(defn- print-handler [component ch {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
  (println (format "[consumer] Received a message: %s, delivery tag: %d, content type: %s, type: %s"
                   (String. payload "UTF-8") delivery-tag content-type type)))

(defn new-domain-event-worker [exchange-name]
  (component/system-map
    :rabbitmq-connection (rabbitmq-new-connection (:rabbitmq-connection analytics-config))
    :worker-print-handler (-> (rabbitmq-new-worker exchange-name "clhorus.analytics.print-handler" print-handler)
                              (component/using [:rabbitmq-connection
                                                :domain-event-publisher]))
    :worker-all-domain-events-persister (-> (rabbitmq-new-worker exchange-name "clhorus.analytics.all_domain_events_persister" all-domain-events-persister/handler)
                                            (component/using [:rabbitmq-connection
                                                              :domain-event-publisher]))))
