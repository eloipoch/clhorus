(ns clhorus.context.analytics.module.user.application.domain-event-handler.create-user-registration-on-user-registered
  (:use clhorus.context.analytics.module.user.application.service.user-registration-creator)
  (:require [clj-uuid :as uuid]))

(defn create-user-registration-on-user-registered [repository-user domain-event]
  (create repository-user (uuid/as-uuid (:user-id domain-event)) (:occurred-on domain-event)))
