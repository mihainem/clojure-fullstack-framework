(ns api.users.db
  (:require [next.jdbc.sql :as sql]
            [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [honeysql.core :as h]
            [honeysql.helpers :as hh]
            [buddy.hashers :as buddy]))


(defn db-query
  ([sql db]
   (with-open [connection (jdbc/get-connection db)]
     (jdbc/execute!  connection sql
                     {:return-keys true
                      :builder-fn rs/as-unqualified-maps}))))


(defn db-query-one
  ([sql db]
   (with-open [connection (jdbc/get-connection db)]
     (jdbc/execute-one! connection sql
                        {:return-keys true
                         :builder-fn rs/as-unqualified-maps}))))


(defn create-user
  ([sql]
   (create-user sql nil))
  ([{:keys [username password email]} db]
   (let [hashed-password (buddy/encrypt password)
         user (-> (hh/insert-into :users)
                  (hh/columns :email :username :password)
                  (hh/values [[email username hashed-password]])
                  h/format
                  (db-query-one db))
         sanitized-user (dissoc user :password)]
     sanitized-user)))


(defn get-user
  [{:keys [username password]} db]
  (let [user (-> (hh/select :*)
                 (hh/from :users)
                 (hh/where := :username username)
                 h/format
                 (db-query-one db))
        sanitized-user (dissoc user :password)]
    (if (and user (buddy/check password (:password user)))
      sanitized-user
      nil)))


(defn get-all-users
  [db]
  (let [users (-> (hh/select :*)
                  (hh/from :users)
                  h/format
                  (db-query db))
        sanitized-users (map #(dissoc % :password) users)]
    {:users sanitized-users}))



