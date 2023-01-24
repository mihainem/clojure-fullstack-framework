(ns xo.router
  (:require [reitit.ring :as ring]
            [xo.routes :as r]))

(defn routes
  [env]
  (ring/ring-handler
   (ring/router
    [["/v1" (r/routes env)]])))
