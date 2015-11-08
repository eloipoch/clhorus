(ns clhorus.lib.rabbitmq.worker.test
  (:require [langohr.core :as rmq]
            [langohr.channel :as lch]
            [langohr.queue :as lq]
            [langohr.consumers :as lc]
            [langohr.basic :as lb]))

(defn publish [exchange-name routing-key payload]
  (let [conn (rmq/connect)
        ch   (lch/open conn)]
    (lb/publish ch exchange-name routing-key payload)
    (rmq/close ch)
    (rmq/close conn)))

(defn publish-domain-events-test []
  (publish "domain_events" "routing-key" "Hello!"))
