(ns user
  (:require
   [integrant.core :as ig]
   [integrant.repl :as ig-repl]
   [integrant.repl.state :as state]
   [api.config :refer [config]]))


(defn ig-prep! []
  (ig/load-namespaces config)
  (ig-repl/set-prep! (fn [] config)))


(defn go []
  (ig-prep!)
  (ig-repl/go))


(defn halt []
  (ig-prep!)
  (ig-repl/halt))


(defn reset []
  (ig-prep!)
  (ig-repl/reset))


(defn reset-all []
  (ig-prep!)
  (ig-repl/reset-all))


(def app (-> state/system :api/router))

(def db (-> state/system :api/db))


(defn -main []
  (go))


(comment
  (go)
  (halt)
  (reset))
