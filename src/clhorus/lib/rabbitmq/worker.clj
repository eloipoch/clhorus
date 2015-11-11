(ns clhorus.lib.rabbitmq.worker
  (:require [com.stuartsierra.component :as component]
            [langohr.core :as rmq]
            [langohr.channel :as lch]
            [langohr.queue :as lq]
            [langohr.consumers :as lc]))

(defrecord RabbitMQWorker [exchange-name queue-name subscriber]
  component/Lifecycle

  (start [component]
    (let [connection (get-in component [:rabbitmq-connection :connection])
          channel    (lch/open connection)]
      (lq/declare channel queue-name {:durable true :exclusive false :auto-delete false})
      (lq/bind channel queue-name exchange-name {:routing-key "#"})
      (lc/subscribe channel queue-name (partial subscriber component) {:auto-ack true})
      (assoc component :channel channel)))

  (stop [component]
    (if-let [channel (:channel component)]
      (rmq/close channel))
    (assoc component :channel nil)))

(defn rabbitmq-new-worker [exchange-name queue-name subscriber]
  (map->RabbitMQWorker {:exchange-name exchange-name
                        :queue-name    queue-name
                        :subscriber    subscriber}))
