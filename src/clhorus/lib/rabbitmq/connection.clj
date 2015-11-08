(ns clhorus.lib.rabbitmq.connection
  (:require [com.stuartsierra.component :as component]
            [langohr.core :as rmq]))

(defrecord RabbitMQConnection []
  component/Lifecycle

  (start [component]
    (assoc component :connection (rmq/connect)))

  (stop [component]
    (rmq/close (:connection component))
    (assoc component :connection nil)))

(defn rabbitmq-new-connection []
  (map->RabbitMQConnection {}))
