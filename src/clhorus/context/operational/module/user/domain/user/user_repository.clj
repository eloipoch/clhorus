(ns clhorus.context.operational.module.user.domain.user.user-repository)

(defprotocol UserRepository
  ;(add-user [this ^User user])
  (add-user [this user])
  (get-user [this user-id])
  )
