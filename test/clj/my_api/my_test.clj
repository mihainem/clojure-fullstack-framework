(ns my-api.my-test
  (:require
   [clojure.test :refer [deftest testing is]]))

(deftest name-test
  (testing "Context of the test assertions"
    (is (= 1 1))))
