(ns api.log
  (:require
   [integrant.core :as ig]
   [taoensso.timbre :as log]
   [taoensso.timbre.tools.logging]))

(defmethod ig/init-key :api/log [_ log-config]
  (taoensso.timbre.tools.logging/use-timbre)
  (log/merge-config! log-config))
