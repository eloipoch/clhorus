(ns clhorus.lib.bus.protocol)

(defprotocol Bus
  (publish [this message])
  (subscribe [this message-class handle])
  (unsubscribe [this id]))
