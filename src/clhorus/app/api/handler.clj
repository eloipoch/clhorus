(ns clhorus.app.api.handler
  (:use clhorus.app.api.controller.user.post)
  (:use clhorus.app.api.routing.user)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
           (GET "/" [] "Clhorus toy project!")
           (routes user-routes)
           (route/not-found {:status 404 :body "Not found"})
           )

(def app
  (wrap-defaults app-routes
                 (assoc-in site-defaults [:security :anti-forgery] false)))
