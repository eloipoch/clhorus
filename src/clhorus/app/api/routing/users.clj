(ns clhorus.app.api.routing.users
  (:use clhorus.app.api.controller.user.post)
  (:require [vertx.http :as http]
            [vertx.http.route :as route]
            [vertx.stream :as stream]
            )
  )

; @todo improve
(defn- route-users-post [matcher]
  (route/post matcher "/users"
              (fn [request]
                (http/expect-multi-part request)
                (stream/on-end request
                               #(users-post (http/form-attributes request))
                               )
                (-> request
                    (http/server-response {:status-code 201})
                    (http/end))
                )
              ))

(defn- route-users-get [matcher]
  (route/get matcher "/users"
             (fn [request]
               (-> request
                   (http/server-response {:status-code 200 :status-message "Not implemented yet :P"})
                   (http/end))
               )
             ))

(defn route-users [matcher]
  (-> matcher
      (route-users-get)
      (route-users-post))
  )
