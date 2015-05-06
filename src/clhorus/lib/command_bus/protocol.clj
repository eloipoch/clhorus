(ns clhorus.lib.command-bus.protocol)

(defprotocol CommandBus
  (handle [this command])
  (register [this command-name handle])
  (unregister [this command-name]))
