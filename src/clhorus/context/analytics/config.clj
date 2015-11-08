(ns clhorus.context.analytics.config)

(def analytics-config
  {:database            {:db       "clhorus_analytics_tests"
                         :host     "127.0.0.1"
                         :user     "root"
                         :password ""}
   :rabbitmq-connection {
                         :host     "localhost"
                         :port     5672
                         :vhost    "/"
                         :username "guest"
                         :password "guest"
                         }
   :rabbitmq-worker      {
                         :exchange-name "domain_events"
                         }
   })
