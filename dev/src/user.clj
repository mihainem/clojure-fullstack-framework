(ns user
  (:require [integrant.repl :as ig-repl]
            [integrant.core :as ig]
            [integrant.repl.state :as state]))


(ig-repl/set-prep!
 (fn [] (-> "resources/config.edn" slurp ig/read-string)))

(def go ig-repl/go)
(def halt ig-repl/halt)
(def reset ig-repl/reset)
(def reset-all ig-repl/reset-all)

(def app  (-> state/system :server/app))
(def db  (-> state/system :db/postgress))

(comment
  (app {:request-method :get
        :uri "/v1/chat"})
  (go)
  (halt)
  (reset))
