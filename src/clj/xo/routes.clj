(ns xo.routes)

(defn routes
  [env]
  ["/chat" {:get  {:handler (fn [req] {:status 200
                                       :body "chat ok"})}}])
