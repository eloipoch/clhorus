(ns clhorus.app.api.handler
  (:use [clhorus.app.api.routing.users]
        [clhorus.app.api.controller.user.post])
  (:require [clhorus.app.api.config :refer [app-api-config]]
            [vertx.http :as http]
            [vertx.http.route :as route]))

(defn- route-not-found [matcher]
  (route/no-match
    matcher
    #(-> %
         (http/server-response {:status-code 404})
         (http/end))))

(defn routes [operational-command-bus]
  (->> (route/matcher)
       (route-users operational-command-bus)
       (route-not-found)
       )
  )

(defn run [context-operational]
  (-> (http/server)
      (http/on-request (routes (:operational-command-bus context-operational)))
      (http/listen (:port app-api-config) (:server app-api-config)))
  )
