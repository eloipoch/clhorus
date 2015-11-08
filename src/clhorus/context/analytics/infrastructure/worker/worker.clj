(ns clhorus.context.analytics.infrastructure.worker.worker
  (:require [clhorus.lib.rabbitmq.worker :refer [rabbitmq-new-worker]]))

(defn- message-handler [component ch {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
  (println "component: " component)
  (println (format "[consumer] Received a message: %s, delivery tag: %d, content type: %s, type: %s"
                   (String. payload "UTF-8") delivery-tag content-type type)))

(defn new-domain-event-worker [exchange-name]
  (rabbitmq-new-worker exchange-name "clhorus.analytics.domain-event" [message-handler]))
