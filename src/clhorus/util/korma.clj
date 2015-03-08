(ns clhorus.util.korma
  (:use clhorus.util.uuid))

(defn translate-uuid-to-binary [field]
  (fn [{id field :as v}]
    (assoc v field (uuid-to-binary id))
    ))
