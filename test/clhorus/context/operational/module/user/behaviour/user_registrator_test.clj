(ns clhorus.context.operational.module.user.behaviour.user-registrator-test
  (:use midje.sweet)
  (:use clhorus.context.operational.module.user.domain.user.user-repository)
  (:use clhorus.context.operational.module.user.domain.user.user)
  (:use clhorus.context.operational.module.user.domain.user.user-id)
  (:use clhorus.context.operational.infrastructure.registry)
  (:require [clojure.test :refer :all]
            [clj-uuid :as uuid]
            [clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler :refer :all]))

(facts "User registrator service"
       (fact "register a new user"
             (with-redefs [user-repository :mocked-repository]
               (let [user-id-string (uuid/to-string (uuid/v4))
                     user-id        (create-user-id user-id-string)]
                 (handle {:command :user-registration-command
                          :user-id user-id-string}) => nil
                 (provided
                   (add user-repository (create-user user-id)) => nil :times 1
                   )
                 )
               )
             )
       )
