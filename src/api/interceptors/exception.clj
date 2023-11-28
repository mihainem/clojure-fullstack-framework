(ns api.interceptors.exception
  (:require
   [reitit.http.interceptors.exception :as exception]
   [taoensso.timbre :as log]))

(defn default-exception-handler
  [^Exception exception _request]
  {:status 500
   :body {:type "exception"
          :class (.getClass exception)
          :message (str (.getMessage exception))}})

(defn debug-handler [handler e request]
  (log/debug "Got exception: " e)
  (tap> e)
  (handler e request))

(defn make-interceptor
  [debug?]
  (exception/exception-interceptor
   (cond-> exception/default-handlers
     true (assoc ::exception/default default-exception-handler)
     debug? (assoc ::exception/wrap debug-handler))))
