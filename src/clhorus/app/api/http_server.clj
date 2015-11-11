(ns clhorus.app.api.http-server
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [org.httpkit.server :refer [run-server]]
            [com.stuartsierra.component :as component]
            [clhorus.app.api.config :refer [app-api-config]]
            [clhorus.app.api.routing.users :refer [routes-users]]
            ))

(defn app-routes [operational-command-bus]
  (routes
    (GET "/" [] "Clhorus toy project!")
    (routes-users operational-command-bus)
    (route/not-found "Not found")))

(defrecord ApplicationApiComponent []
  component/Lifecycle

  (start [component]
    (println "  Starting Application API...")
    (assoc component :stop-server
                     (run-server
                       (wrap-defaults
                         (app-routes (get-in component [:context-operational :operational-command-bus]))
                         (assoc-in site-defaults [:security :anti-forgery] false))
                       app-api-config)
                     ))

  (stop [component]
    (println "  Stopping Application API...")
    (if-let [stop-server (:stop-server component)]
      (stop-server))
    (dissoc component :stop-server)))

(defn new-application-api []
  (map->ApplicationApiComponent {}))