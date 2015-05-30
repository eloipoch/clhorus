(ns clhorus.context.operational.config)

(def operational-config
  {:domain-event-address-name "domain-event"
   :database                  {:db       "clhorus_operational_tests"
                               :host     "127.0.0.1"
                               :user     "root"
                               :password ""}})
