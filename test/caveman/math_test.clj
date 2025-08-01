(ns caveman.math-test
  (:require [caveman.test-system :as test-system]
            [clojure.test :as t]
            [next.jdbc :as jdbc]))

(t/deftest one-plus-one
  (t/is (= (+ 1 1) 2) "One plus one equals 2!"))

(t/deftest counting-works
  (test-system/with-test-db
    (fn [db]
      (jdbc/execute! db ["INSERT INTO prehistoric.hominid (name) VALUES (?)" "Grunto"])
      (jdbc/execute! db ["INSERT INTO prehistoric.hominid (name) VALUES (?)" "Blingus"])
      (t/is (= (:count (jdbc/execute-one! db ["SELECT COUNT(*) as count FROM prehistoric.hominid"]))
               2)))))

