(ns xo.routes)

(defn routes
  [_env]
  ["/chat" {:get  {:handler (fn [_req]
                              {:status 200
                               :body "chat ok"})}}])
