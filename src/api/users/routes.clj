(ns api.users.routes
  (:require [api.users.handlers :as handle]
            [schema.core :as s]
            [api.responses :as resp]))


(defn routes
  [env]
  (let [db (:jdbc-url env)]
    [["/users" {:get {:handler (handle/get-all-users db)
                      #_:responses #_{200 {:body resp/users}}}
                :summary "list all users"}]
     ["/login" {:post {:parameters {:body {:username s/Str
                                           :password s/Str}}
                       :handler (handle/login db)}}]
     ["/register" {:post {:parameters {:body {:username s/Str
                                              :password s/Str
                                              :email s/Str}}
                          :handler (handle/register db)}}]]))
