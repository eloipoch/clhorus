(ns clhorus.app.api.controller.user.post
  (:use clhorus.app.api.infrastructure.registry)
  (:require [clhorus.context.operational.module.user.contract.command.user-registration-command])
  (:import (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))

(defn users-post [{user-id :id}]
  (operational-command-bus-handle (UserRegistrationCommand. user-id))
  {:status 201})
