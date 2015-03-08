(ns clhorus.app.api.controller.user.post
  (:use clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler))

; @todo use a command bus
(defn users-post [{{user-id :id} :params}]
  (handle {:command :user-registration-command
           :user-id user-id})
  {:status 201})
