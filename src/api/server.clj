(ns api.server
  (:require
   [integrant.core :as ig]
   [ring.adapter.jetty9 :as jetty]
   [aero.core]))


(defmethod ig/init-key :api/server
  [_ config]
  (let [{:keys [port handler]} config]
    (println (str "Server running on port " port))
    (jetty/run-jetty handler {:port port :join? false})))

(defmethod ig/halt-key! :api/server
  [_ server]
  (.stop server))


