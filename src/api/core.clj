(ns api.core
  (:require
   [integrant.core :as ig]
   [api.config :refer [config]])
  (:gen-class))


(defn -main
  ([] (-main config))
  ([config-content]
   (ig/load-namespaces config-content)
   (-> config-content
       ig/prep
       ig/init)))
