;(ns clhorus.context.operational.module.user.behaviour.user-registrator-test
;  (:use midje.sweet)
;  (:require
;    [clojure.test :refer [deftest is use-fixtures]]
;    [vertx.testtools :as t]
;    [clj-uuid :as uuid]
;    [clhorus.context.operational.module.user.application.command-handler.user-registration-command-handler :refer [handle]]
;    [clhorus.context.operational.module.user.contract.command.user-registration-command]
;    [clhorus.context.operational.module.user.domain.user.user-repository :refer [add]]
;    [clhorus.context.operational.module.user.domain.user.user :refer [create-user]]
;    [clhorus.context.operational.module.user.domain.user.user-id :refer [create-user-id]])
;  (:import (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)))
;
;(defn user-repository [])
;(defn publisher [_])
;
;(facts "User registrator service"
;       (fact "register a new user"
;             (t/as-embedded
;               (fn []
;                 (with-redefs [user-repository :mocked-repository
;                               publisher :mocked-publisher]
;                   (let [user-id-string            (uuid/to-string (uuid/v4))
;                         user-id                   (create-user-id user-id-string)
;                         [user domain-events] (create-user user-id)
;                         user-registration-command (UserRegistrationCommand. user-id-string)]
;                     (handle user-repository publisher user-registration-command) => nil
;                     (provided
;                       (add user-repository user) => nil :times 1
;                       (publisher domain-events) => nil :times 1
;                       )
;                     )
;                   )
;                 (t/test-complete)
;                 )
;               )
;             )
;       )
