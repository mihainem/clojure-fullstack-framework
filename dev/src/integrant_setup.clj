(ns integrant-setup
  (:require
    [environ.core :refer [env]]
    [integrant.core :as ig]
    [integrant.repl :as ig-repl]
    [integrant.repl.state :as state]
    [ring.adapter.jetty9 :as jetty]
    [xo.core :as xo]))

(defmethod ig/prep-key :server/jetty
  [_ config]
  ;; config)
  (merge config {:port (or (Integer/parseInt (env :port))
                           (:port config))}))

(defmethod ig/init-key :server/jetty
  [_ {:keys [port handler]}]
  (println (str "Server running on port " port))
  (jetty/run-jetty handler {:port port :join? false}))

(defmethod ig/init-key :server/app
  [_ config]
  (println "app started")
  (xo/app config))

(defmethod ig/init-key :db/postgres
  [_ config]
  (println "db started")
  (:jdbc-url config))

(defmethod ig/halt-key! :server/jetty
  [_ server]
  (.stop server))

(ig-repl/set-prep!
  (fn [] (-> "resources/config.edn" slurp ig/read-string)))

(def go ig-repl/go)
(def halt ig-repl/halt)
(def reset ig-repl/reset)
(def reset-all ig-repl/reset-all)

(def app  (-> state/system :server/app))
(def db  (-> state/system :db/postgress))
