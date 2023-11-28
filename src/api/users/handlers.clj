(ns api.users.handlers
  (:require [api.users.db :as users-db]
            [ring.util.response :as rr]))



(defn get-all-users
  [db]
  (fn [_]
    (let [users (users-db/get-all-users db)]
      (->> (:users users)
           (map #(dissoc % :users/password))
           rr/response))))

(defn register
  [db]
  (fn [{:keys [parameters]}]
    (let [data (:body parameters)
          user (users-db/create-user data db)]
      (rr/response user))))


(defn login
  [db]
  (fn [{:keys [parameters]}]
    (let [data (:body parameters)
          user (users-db/get-user data db)]
      (if (nil? user)
        {:status 404
         :body {:error "Invalid credentials"}}
        {:status 200
         :body {:user user}}))))
