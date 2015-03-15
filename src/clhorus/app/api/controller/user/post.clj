(ns clhorus.app.api.controller.user.post
  (:use clhorus.app.api.infrastructure.registry))

(defn users-post [{user-id :id}]
  (operational-command-bus-handle {:command :user-registration-command
                                   :user-id user-id})
  {:status 201})
