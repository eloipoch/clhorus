(ns clhorus.context.analytics.module.user.domain.user-registration.user-registration-repository)

(defprotocol UserRegistrationRepository
  (add [this user-registration]))
