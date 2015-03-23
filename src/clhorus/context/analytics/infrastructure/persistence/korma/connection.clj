(ns clhorus.context.analytics.infrastructure.persistence.korma.connection
  (:use clhorus.context.analytics.infrastructure.config))
(use 'korma.db)

(require '[clojure.string :as str])

(defdb analytics-db config-analytics-db)
