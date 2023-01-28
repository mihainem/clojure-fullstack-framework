(ns xo.core
  (:require
    [xo.router :as router]))

(defn app
  [env]
  (router/routes env))

