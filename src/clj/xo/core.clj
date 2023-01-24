(ns xo.core
  (:require ;;[org.httpkit.server :refer [run-server]]
   [reitit.ring :as ring]
   [integrant.core :as ig]
   [ring.adapter.jetty9 :as jetty]
   [xo.router :as router]))



(defn app [env]
  (router/routes env))

(defmethod ig/prep-key :server/jetty
  [_ config]
  config)
 ;; (merge config {:port (Integer/parseInt (env :port))}))

(defmethod ig/init-key :server/jetty
  [_ {:keys [port handler]}]
  (println (str "Server running on port " port))
  (jetty/run-jetty handler {:port port :join? false}))

(defmethod ig/init-key :server/app
  [_ config]
  (println "app started")
  (app config))

(defmethod ig/init-key :db/postgres
  [_ config]
  (println "db started")
  (:jdbc-url config))

(defmethod ig/halt-key! :server/jetty
  [_ server]
  (.stop server))

(defn -main
  [config-file]
  (let [config (-> config-file slurp ig/read-string)]
    (-> config ig/prep ig/init)))

