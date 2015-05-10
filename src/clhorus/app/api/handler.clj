;(ns clhorus.app.api.handler
;  (:use [clhorus.app.api.routing.users]
;        [clhorus.app.api.controller.user.post])
;  (:require [clhorus.app.api.config :refer [app-api-config]]
;            [vertx.http :as http]
;            [vertx.http.route :as route]))
;
;(defn- route-not-found [matcher]
;  (route/no-match
;    matcher
;    #(-> %
;         (http/server-response {:status-code 404})
;         (http/end))))
;
;(defn routes [operational-command-bus]
;  (->> (route/matcher)
;       (route-users operational-command-bus)
;       (route-not-found)
;       )
;  )
;
;(defn run [context-operational]
;  (-> (http/server)
;      (http/on-request (routes (:operational-command-bus context-operational)))
;      (http/listen (:port app-api-config) (:server app-api-config)))
;  )

(ns clhorus.app.api.handler
  (:use clhorus.app.api.controller.user.post)
  (:use clhorus.app.api.routing.users)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [org.httpkit.server :refer [run-server]]
            [com.stuartsierra.component :as component]))

(defn app-routes [operational-command-bus]
  (routes
    (GET "/" [] "Clhorus toy project!")
    (POST "/users" request (users-post operational-command-bus request))
    (GET "/foo" [] "Hello Foo")))

(defrecord ApplicationApiComponent []
  component/Lifecycle

  (start [component]
    (println "  Starting Application API...")
    (assoc component :stop-server
                     (run-server
                       (wrap-defaults
                         (app-routes (get-in component [:context-operational :operational-command-bus]))
                         (assoc-in site-defaults [:security :anti-forgery] false))
                       {:port 8080})
                     ))

  (stop [component]
    (println "  Stopping Application API...")
    ((:stop-server component))
    (dissoc component :stop-server)))
