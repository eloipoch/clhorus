(ns clhorus.context.operational.module.user.domain.user.user-repository)

(defprotocol UserRepository
  ;(add-user [this ^User user])
  (add [this user])
  (search [this user-id])
  )
