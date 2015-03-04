(ns clhorus.context.operational.infrastructure.config)

(use 'korma.db)

(def config-operational-db (mysql {:db       "clhorus_operational_tests"
                                   :host     "127.0.0.1"
                                   :user     "root"
                                   :password ""}))

