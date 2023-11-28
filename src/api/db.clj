(ns api.db
  (:require
   [integrant.core :as ig]
   [api.migrations :as migrator]))


(defmethod ig/init-key :api/db
  [_ config]
  (println "DB started init ")
  (-> (:jdbc-url config)
      (migrator/migrate-up))
  (:jdbc-url config))
