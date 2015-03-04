(ns clhorus.app.api.routing.user
  (:use clhorus.app.api.controller.user.post)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))

(defroutes user-routes
           (POST "/users" request (users-post request))
           )
