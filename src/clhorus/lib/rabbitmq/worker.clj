(ns clhorus.lib.rabbitmq.worker
  (:require [com.stuartsierra.component :as component]
            [langohr.core :as rmq]
            [langohr.channel :as lch]
            [langohr.queue :as lq]
            [langohr.consumers :as lc]))

; @todo send to callback not all the component
(defrecord RabbitMQWorker [exchange-name queue-name subscribers]
  component/Lifecycle

  (start [component]
    (let [connection (get-in component [:rabbitmq-connection :connection])
          channel    (lch/open connection)]
      (lq/declare channel queue-name {:durable true :exclusive false :auto-delete false})
      (lq/bind channel queue-name exchange-name {:routing-key "#"})
      (apply #(lc/subscribe channel queue-name (partial % component) {:auto-ack true}) subscribers)
      (assoc component :channel channel)))

  (stop [component]
    (rmq/close (:channel component))
    (assoc component :channel nil)))

(defn rabbitmq-new-worker [exchange-name queue-name subscribers]
  (map->RabbitMQWorker {:exchange-name exchange-name
                        :queue-name    queue-name
                        :subscribers   subscribers}))
