(ns clhorus.context.operational.module.user.behaviour.user-registrator-test
  (:use midje.sweet)
  (:use clhorus.context.operational.module.user.domain.user.user-repository)
  (:use clhorus.context.operational.module.user.domain.user.user)
  (:use clhorus.context.operational.infrastructure.registry)
  (:require [clojure.test :refer :all]
            [clhorus.context.operational.module.user.application.service.user-registrator :refer :all]))

(facts "User registrator service"
       (fact "register a new user"
             (with-redefs [user-repository :mocked-repository]
               (register-user ...user-id...) => nil
               (provided
                 (add user-repository (create-user ...user-id...)) => nil :times 1
                 )
               )
             )
       )
