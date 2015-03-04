(ns clhorus.app.api.controller.user.post
  (:use clhorus.context.operational.module.user.application.service.user-registrator))

(defn users-post [{{user-id :id} :params}]
  (register-user user-id)
  {:status 201})
