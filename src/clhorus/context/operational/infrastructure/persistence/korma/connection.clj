(ns clhorus.context.operational.infrastructure.persistence.korma.connection
  (:use clhorus.context.operational.infrastructure.config))
(use 'korma.db)

(require '[clojure.string :as str])

(defdb operational-db config-operational-db)
