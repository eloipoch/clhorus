(ns clhorus.context.operational.infrastructure.persistence.korma-component
  (:use korma.db)
  (:require [com.stuartsierra.component :as component]))

(defrecord DatabaseKormaComponent [config]
  component/Lifecycle

  (start [component]
    (assoc component :database (create-db (mysql config))))

  (stop [component]
    (assoc component :database nil)))
