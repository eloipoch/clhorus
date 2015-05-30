(ns clhorus.app.api.routing.users
  (:require [compojure.core :refer :all]
            [clhorus.app.api.controller.user.post :refer [users-post]]))

(defn routes-users [operational-command-bus]
  (routes
    (GET "/users" [] "Not implemented yet :P")
    (POST "/users" request (users-post operational-command-bus request))))
