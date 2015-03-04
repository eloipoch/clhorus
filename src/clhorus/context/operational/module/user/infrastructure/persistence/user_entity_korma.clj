(ns clhorus.context.operational.module.user.infrastructure.persistence.user-entity-korma
  (:use clhorus.context.operational.infrastructure.persistence.korma.connection)
  (:use clhorus.util.uuid))

(use 'korma.core)

(declare user)

(defentity user-entity-korma
           ;; Basic configuration
           (pk :id)                                         ;; by default "id". This line is unnecessary.
           ;; it's used for relationships joins.
           (table :user)                                    ;; by default the name of the symbol.
           ;; The line above is also unecessary.
           (database operational-db)                        ;; if none is specified the last defdb will be used. Also unnecessary.
           (entity-fields :id)                              ;; default fields for selects
           ;; Mutations
           (prepare (fn [{id :id :as v}]
                      (assoc v :id (uuid-to-binary id))
                      ))
           ;; apply a function before storing in the db
           ;; in this case the function changes the "last" field
           ;; to upper case.
           ;(transform (fn [{first :first :as v}]
           ;             (if first
           ;               (assoc v :first (str/capitalize first)) v)))
           ;; apply a function to all select results
           ;; in this case the function changes the "first" field
           ;; to capitalized.
           )

