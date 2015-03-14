(ns clhorus.app.api.handler
  (:use clhorus.app.api.controller.user.post)
  (:use clhorus.context.analytics.module.user.application.domain-event-handler.create-user-registration-on-user-registered)
  (:require [vertx.http :as http]
            [vertx.http.route :as route]
            [vertx.stream :as stream]
            ))

; @todo improve
(defn init []
  (subscriber)
  (-> (http/server)
      (http/on-request
        (-> (route/post "/users"
                        (fn [request]
                          (http/expect-multi-part request)
                          (stream/on-end request
                                         #(users-post (http/form-attributes request))
                                         )
                          (-> request
                              (http/server-response {:status-code 201})
                              (http/end))
                          )
                        )
            (route/no-match
              #(-> %
                   (http/server-response {:status-code 404})
                   (http/end)))
            ))
      (http/listen 8080 "localhost"))
  )
