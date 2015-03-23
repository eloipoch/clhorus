(ns clhorus.context.analytics.infrastructure.config)

(use 'korma.db)

(def config-analytics-db (mysql {:db       "clhorus_analytics_tests"
                                 :host     "127.0.0.1"
                                 :user     "root"
                                 :password ""}))

