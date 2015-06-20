(ns clhorus.context.operational.module.user.behaviour.user-registrator-test
  (:use midje.sweet)
  (:use clhorus.context.operational.module.user.domain.user.user-repository)
  (:use clhorus.context.operational.module.user.domain.user.user)
  (:use clhorus.context.operational.module.user.domain.user.user-id)

  (:require [com.stuartsierra.component :as component]
            [clhorus.lib.domain-event.channel :refer [new-domain-event-publisher-channel]]
            [clhorus.lib.command-bus.channel :refer [new-command-bus-channel]]
            [clhorus.lib.command-bus.protocol :as cb]
            [clhorus.context.operational.module.user.user-module-component :refer [user-module-system]]
            [clhorus.context.operational.infrastructure.persistence.korma-component]
            [clhorus.context.operational.module.user.contract.command.user-registration-command]
            [clhorus.context.operational.module.user.domain.user.user]
            [clhorus.context.operational.module.user.infrastructure.command-bus.handlers-component]
            [clj-uuid :as uuid])
  (:import (clhorus.context.operational.infrastructure.persistence.korma_component DatabaseKormaComponent)
           (clhorus.context.operational.module.user.contract.command.user_registration_command UserRegistrationCommand)
           (clhorus.context.operational.module.user.infrastructure.command_bus.handlers_component CommandBusHandlersComponent)))

; @todo move to test infrastructure
(defrecord UserRepositoryMocked []
  UserRepository
  (add [_ _]))

(defn test-user-module-system []
  (component/system-map
    :repository-user (UserRepositoryMocked.)
    :command-bus-operational-user-handlers (component/using (CommandBusHandlersComponent.) [:operational-command-bus
                                                                                            :repository-user
                                                                                            :domain-event-publisher])))

(defn test-context-operational-system []
  (component/system-map
    :database-operational (DatabaseKormaComponent. (:database :mocked))
    :operational-command-bus (new-command-bus-channel)
    :user-module (component/using (test-user-module-system) [:domain-event-publisher
                                                             :database-operational
                                                             :operational-command-bus])
    ))

(defn test-clhorus-system []
  (component/system-map
    :domain-event-publisher (new-domain-event-publisher-channel)
    :context-operational (component/using (test-context-operational-system) [:domain-event-publisher])
    ))

(defn start-test-clhorus-system []
  (component/start (test-clhorus-system)))

(facts "User registrator service"
       (fact "register a new user"
             (let [user-id-string            (-> (uuid/v4) (uuid/to-string))
                   user-registration-command (UserRegistrationCommand. user-id-string)
                   system                    (start-test-clhorus-system)
                   operational-command-bus   (get-in system [:context-operational :operational-command-bus])
                   ]
               (cb/handle operational-command-bus user-registration-command) => anything
               )))
