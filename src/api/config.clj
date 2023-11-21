(ns api.config
  (:require [aero.core :as aero]
            [integrant.core :as ig]
            [clojure.java.io :as io]))


(defmethod aero/reader 'ig/ref
  [_opts _tag value]
  (ig/ref value))


(def config
  (or (-> "config/dev/config.edn"
          aero/read-config)
      (-> "config.edn"
          io/resource
          aero/read-config)))
  

