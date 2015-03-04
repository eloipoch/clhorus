(ns clhorus.util.uuid
  (:import (java.util UUID)))

(defn- uuid-from-string [uuid-string] (UUID/fromString uuid-string))

(defn- uuid-as-byte-array [uuid]
  (let [u uuid                                              ; uuid4
        ^long lo (.getLeastSignificantBits u)               ; least significant bits
        ^long hi (.getMostSignificantBits u)]               ; most significant bits (left-most bits)
    (-> (java.nio.ByteBuffer/allocate 16)                   ; http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html
        (.putLong hi)
        (.putLong lo)
        (.array))))

(defn uuid-to-binary [uuid-string]
  (uuid-as-byte-array (uuid-from-string uuid-string)))
