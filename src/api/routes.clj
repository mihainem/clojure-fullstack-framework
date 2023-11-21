(ns api.routes
  (:require [api.handlers :as handle]))


(defn routes
  [env]
  (let [db (:jdbc-url env)]
    [["/users" {:get {:handler (handle/list-all-users db)}
                :summary "list all users"}]]))
