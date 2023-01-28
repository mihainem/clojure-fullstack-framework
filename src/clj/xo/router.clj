(ns xo.router
  (:require
   [muuntaja.core :as m]
   [reitit.ring :as ring]
   [reitit.ring.middleware.exception :as rrmex]
   [reitit.ring.middleware.parameters :as rrmp]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.coercion :as coercion]
   [reitit.coercion.schema]
   [reitit.swagger :as swagger]
   [reitit.swagger-ui :as swagger-ui]
   [xo.routes :as r]))

(def swagger-docs
  ["/swagger.json"
   {:get {:no-doc true
          ;; :summary "Swagger JSON"
          :swagger {:basePath "/"
                    :info {:title "XO API"
                           :description "XO API"
                           :version "1.0.0"}}
          ;; :responses {200 {:schema swagger/Schema}}
          :handler (swagger/create-swagger-handler)}}])

(def router-config
  {:data {:coercion   reitit.coercion.schema/coercion
          :muuntaja m/instance
          :middleware [swagger/swagger-feature
                       rrmp/parameters-middleware
                       muuntaja/format-negotiate-middleware
                       muuntaja/format-response-middleware
                       rrmex/exception-middleware
                       muuntaja/format-request-middleware
                       coercion/coerce-exceptions-middleware
                       coercion/coerce-request-middleware
                       coercion/coerce-response-middleware]}})

(defn routes
  [env]
  (ring/ring-handler
   (ring/router
    [swagger-docs
     ["/v1"
      (r/routes env)]]
    router-config)
   (ring/routes
    (swagger-ui/create-swagger-ui-handler {:path "/"})
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler
     {:not-found (constantly {:status 404
                              :body "Route not found"})}))))
