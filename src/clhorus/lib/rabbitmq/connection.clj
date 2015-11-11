(ns clhorus.lib.rabbitmq.connection
  (:require [com.stuartsierra.component :as component]
            [langohr.core :as rmq]))

(defrecord RabbitMQConnection [settings]
  component/Lifecycle

  (start [component]
    (assoc component :connection (rmq/connect settings)))

  (stop [component]
    (if-let [connection (:connection component)]
      (rmq/close connection))
    (assoc component :connection nil)))

(defn rabbitmq-new-connection [settings]
  (map->RabbitMQConnection {:settings settings}))
