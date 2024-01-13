(ns clojure-training-2024-extracurricular.extra01)

;; Fill functions in

;; Run all tests to test
;; (f1 -> Calva: Run Tests for Current Namespace)

;;;; Maps (hash-maps)
;; Use map manipulation functions (assoc dissoc update etc.)

(def school-records {1001 {:name    "John"
                           :type    :student
                           :classes #{:history :math :art}
                           :grades  [43 65 23 43 56]}
                     1002 {:name    "Ross"
                           :type    :student
                           :classes #{:sports :art :english :math}
                           :grades  [75 59 67 34]}
                     2003 {:name    "Harris"
                           :type    :teacher
                           :salary  50000}})

;; sample
;; Change the name of Ross to Hoss
(defn q1 []
  ;; You can define a function locally in a function
  (defn update-name [record]
    (assoc record :name "Hoss"))

  (update school-records 1002 update-name))

;; Promote harris to :type :principle,
;; he should also receive a 20% increase in salary
(defn q2 [])

;; John should also do the class :science
(defn q3 [])

;; Students should have a new entry in their record :total-grade TOTAL-GRADE
(defn q4 [])

;; expel john, he shouldn't exist in the school-records anymore
(defn q5 [])


;;;; map

;; use (map ...)

(def numbers [1 2 3 4 5])

;; double all the numbers
(defn q6 [])

;; convert all the numbers to string
(defn q7 [])

(def people [{:name "Bob"
              :gender :male}
             {:name "Harry"
              :gender :male}
             {:name "Xinqui"
              :gender :male}
             {:name "Xiangling"
              :gender :female}])

(def job {"Bob"       "Unemployed"
          "Harry"     "Wizard"
          "Xinqui"    "Support"
          "Xiangling" "Chef"})

;; add "Mister " or "Miss " infront of names depending on gender
;; e.g. {:name "John" :gender :male} -> {:name "Mister John" ...}
(defn q8 [])

;; using the job map (DO NOT HARD CODE VALUES WITHOUT USING THE MAP)
;; assign everyone their jobs in :job JOB_HERE
(defn q9 [])

(def people2 [{:name      "Venti"
               :hp        100
               :tasks     [:steal :get-drunk :rest]
               :inventory []}
              {:name      "Xinqui"
               :hp        100
               :tasks     [:explore :battle :battle :rest :battle]
               :inventory []}
              {:name      "Xiangling"
               :hp        100
               :tasks     [:cook :rest :cook :cook]
               :inventory []}])

(def task-outcomes {:steal     (fn [person] (assoc person :wanted true))
                    :get-drunk (fn [person] (assoc person :drunk true))
                    :rest      (fn [person] (update person :hp + 10))
                    :explore   (fn [person] (-> person
                                                (update :hp - 7)
                                                (update :inventory conj :treasure)))
                    :battle    (fn [person] (update person :hp - 30))
                    :cook      (fn [person] (update person :inventory conj :food))})

;; process all the tasks for every person in people2.
;; each person will need every task applied from to themselves
;; tasks should be an empty vector afterwards as each person will have done them
;; use the functions mapped in task-outcomes to do each task
;; GET THE FUNCTIONS FROM task-outcomes DO NOT HARD CODE THEM IN.

(defn q10 [])