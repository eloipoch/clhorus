(ns clhorus.app.api.routing.users
  (:use clhorus.app.api.controller.user.post)
  (:require [vertx.http :as http]
            [vertx.http.route :as route]
            [vertx.stream :as stream]
            )
  )

; @todo improve
(defn- route-users-post [system matcher]
  (route/post matcher "/users"
              (fn [request]
                (http/expect-multi-part request)
                (stream/on-end request
                               #(users-post system (http/form-attributes request))
                               )
                (-> request
                    (http/server-response {:status-code 201})
                    (http/end))
                )
              ))

(defn- route-users-get [system matcher]
  (route/get matcher "/users"
             (fn [request]
               (-> request
                   (http/server-response {:status-code 200 :status-message "Not implemented yet :P"})
                   (http/end))
               )
             ))

(defn route-users [system matcher]
  (->> matcher
       (route-users-get system)
       (route-users-post system))
  )
