(ns clhorus.app.api.handler
  (:use [clhorus.app.api.routing.users]
        [clhorus.app.api.controller.user.post])
  (:require [vertx.http :as http]
            [vertx.http.route :as route]
            ))

(defn- route-not-found [matcher]
  (route/no-match
    matcher
    #(-> %
         (http/server-response {:status-code 404})
         (http/end))))

(defn routes [system]
  (->> (route/matcher)
       (route-users system)
       (route-not-found)
       )
  )

(defn run [system]
  (-> (http/server)
      (http/on-request (routes system))
      (http/listen 8080 "localhost"))
  )
