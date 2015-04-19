(ns clhorus.context.operational.infrastructure.persistence.korma.connection
  (:use korma.db)
  (:require [com.stuartsierra.component :as component]))

; @fixme use config
(defrecord DatabaseKormaComponent [config]
  component/Lifecycle

  (start [component]
    (assoc component :database (create-db (mysql {:db       "clhorus_operational_tests"
                                                  :host     "127.0.0.1"
                                                  :user     "root"
                                                  :password ""}))))

  (stop [component]
    (assoc component :database nil)))
