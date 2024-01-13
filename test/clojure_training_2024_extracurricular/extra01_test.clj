(ns clojure-training-2024-extracurricular.extra01-test
  (:require [clojure.test :refer :all]
            [clojure-training-2024-extracurricular.extra01 :refer :all]))

(deftest hash-map-tests
  (testing
   (testing "q1"
     (is (= {1001 {:name    "John"
                   :type    :student
                   :classes #{:history :math :art}
                   :grades  [43 65 23 43 56]}
             1002 {:name    "Hoss"
                   :type    :student
                   :classes #{:sports :art :english :math}
                   :grades  [75 59 67 34]}
             2003 {:name    "Harris"
                   :type    :teacher
                   :salary  50000}}
            (q1))))
    (testing "q2"
      (is (= {1001 {:name    "John"
                    :type    :student
                    :classes #{:history :math :art}
                    :grades  [43 65 23 43 56]}
              1002 {:name    "Ross"
                    :type    :student
                    :classes #{:sports :art :english :math}
                    :grades  [75 59 67 34]}
              2003 {:name    "Harris"
                    :type    :principle
                    :salary  60000}}
             (q2))))
    (testing "q3"
      (is (= {1001 {:name    "John"
                    :type    :student
                    :classes #{:history :math :art :science}
                    :grades  [43 65 23 43 56]}
              1002 {:name    "Ross"
                    :type    :student
                    :classes #{:sports :art :english :math}
                    :grades  [75 59 67 34]}
              2003 {:name    "Harris"
                    :type    :teacher
                    :salary  50000}}
             (q3))))
    (testing "q4"
      (is (= {1001 {:name    "John"
                    :type    :student
                    :classes #{:history :math :art}
                    :grades  [43 65 23 43 56]
                    :total-grade 230}
              1002 {:name    "Ross"
                    :type    :student
                    :classes #{:sports :art :english :math}
                    :grades  [75 59 67 34]
                    :total-grade 235}
              2003 {:name    "Harris"
                    :type    :teacher
                    :salary  50000}}
             (q4))))
    (testing "q5"
      (is (= {1002 {:name    "Ross"
                    :type    :student
                    :classes #{:sports :art :english :math}
                    :grades  [75 59 67 34]}
              2003 {:name    "Harris"
                    :type    :teacher
                    :salary  50000}}
             (q5))))))

(deftest map-tests
  (testing
   (testing "q6"
     (is (= (seq [2 4 6 8 10])
            (seq (q6)))))
    (testing "q7"
      (is (= (seq ["1" "2" "3" "4" "5"])
             (seq (q7)))))
    (testing "q8"
      (is (= (seq [{:name "Mister Bob", :gender :male}
                   {:name "Mister Harry", :gender :male}
                   {:name "Mister Xinqui", :gender :male}
                   {:name "Miss Xiangling", :gender :female}])
             (seq q8))))
    (testing "q9"
      (is (= (seq [{:name "Bob", :gender :male, :job "Unemployed"}
                   {:name "Harry", :gender :male, :job "Wizard"}
                   {:name "Xinqui", :gender :male, :job "Support"}
                   {:name "Xiangling", :gender :female, :job "Chef"}])
             (seq (q9)))))
    (testing "q10"
      (is (= (seq [{:name "Venti", :hp 110, :tasks [], :inventory [], :wanted true, :drunk true}
                   {:name "Xinqui", :hp 13, :tasks [], :inventory [:treasure]}
                   {:name "Xiangling", :hp 110, :tasks [], :inventory [:food :food :food]}])
             (seq (q10)))))))