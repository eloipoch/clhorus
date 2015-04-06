(ns clhorus.context.operational.infrastructure.persistence.korma.connection
  (:require [com.stuartsierra.component :as component]))
(use 'korma.db)

(require '[clojure.string :as str])

;(def config-operational-db (mysql {:db       "clhorus_operational_tests"
;                                   :host     "127.0.0.1"
;                                   :user     "root"
;                                   :password ""}))
;
;(defdb operational-db config-operational-db)

(defrecord DatabaseKormaComponent [config]
  component/Lifecycle

  (start [component]
    (assoc component :database (mysql {:db       "clhorus_operational_tests"
                                       :host     "127.0.0.1"
                                       :user     "root"
                                       :password ""})))

  (stop [component]
    (assoc component :database nil)))
