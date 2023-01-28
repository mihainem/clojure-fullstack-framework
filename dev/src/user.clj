(ns user
  (:require
    [integrant-setup :refer [app go halt reset]]
    [integrant.core :as ig]))

(defn backend
  [config]
  (-> config ig/prep ig/init))

(defn dev
  [config-file]
  (let [config (-> config-file slurp ig/read-string)]
    (backend config)))

(comment
  (app {:request-method :get
        :uri "/v1/chat"})
  (go)
  (halt)
  (reset))
