(ns clhorus.context.operational.module.user.domain.user.user-repository)

(defprotocol UserRepository
  (add [this user])
  (search [this user-id])
  )
