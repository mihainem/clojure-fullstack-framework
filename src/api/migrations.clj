(ns api.migrations
  (:require    [ragtime.jdbc :as jdbc]
               [ragtime.repl :as rag-repl]))


(defn ragtime-config
  ([jdbc-url]
   {:datastore  (jdbc/sql-database jdbc-url)
    :migrations (jdbc/load-resources "migrations")}))

(defn migrate-up
  ([config]
   (rag-repl/migrate (ragtime-config config))))


(defn migrate-down
  ([config]
   (rag-repl/rollback (ragtime-config config))))
