(ns clhorus.util.uuid
  (:require [clj-uuid :as uuid])
  (:import [java.util UUID]))

(defn uuid-to-binary [^UUID uuid]
  (-> (uuid/to-byte-vector uuid)
      (byte-array))
  )
