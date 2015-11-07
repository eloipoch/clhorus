(ns clhorus.context.analytics.module.user.infrastructure.worker.domain-event.worker
  (:require [com.stuartsierra.component :as component]
            [langohr.core :as rmq]
            [langohr.channel :as lch]
            [langohr.queue :as lq]
            [langohr.consumers :as lc]
            [langohr.basic :as lb]))

(defn callback [domain-event-publisher]
  (println "callback " domain-event-publisher))

(defn message-handler
  [ch {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
  (println (format "[consumer] Received a message: %s, delivery tag: %d, content type: %s, type: %s"
                   (String. payload "UTF-8") delivery-tag content-type type)))

(defrecord DomainEventWorker []
  component/Lifecycle

  (start [component]
    (let [domain-event-publisher   (:domain-event-publisher component)
          conn                     (rmq/connect)
          ch                       (lch/open conn)
          qname                    "clhorus.domain-event"
          message-handler-callback (partial callback domain-event-publisher)]
      (println "starting domain event worker...")
      (println domain-event-publisher)
      (lq/declare ch qname {:exclusive false :auto-delete true})
      (lc/subscribe ch qname message-handler {:auto-ack true})

      (-> component
          (assoc :conn conn)
          (assoc :ch ch))))

  (stop [component]
    (rmq/close (:ch component))
    (rmq/close (:conn component))
    (-> component
        (assoc :conn nil)
        (assoc :ch nil))))

(defn new-domain-event-worker []
  (map->DomainEventWorker {}))
