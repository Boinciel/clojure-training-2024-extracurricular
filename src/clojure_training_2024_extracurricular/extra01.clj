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
(defn q2 []
  (defn become-moral-principle [record]
    (-> record
        (assoc :type :principle)
        (update :salary * 1.2)
        (update :salary int)))
  (update school-records 2003 become-moral-principle))

(q2)
;; John should also do the class :science
(defn q3 []
  (defn join-class [record class]
    (-> record
        (update :classes conj class)))
  
  (update school-records 1001 join-class :science))

(q3)

;; Students should have a new entry in their record :total-grade TOTAL-GRADE
(defn q4 []
  (defn sum-coll [coll]
    (apply + coll))
  (defn student? [member]
    (if (= (get member :type) :student)
      true
      false))
  (defn get-total-grade [student]
    (sum-coll (-> student
                  (second)
                  (get :grades))))
  (defn assign-total-grades [member]
    (if (student? member)
        (update member :total-grade (get-total-grade member))
        member))
  
  ;; (map assign-total-grades school-records))
  (assign-total-grades (first school-records)))

(q4)

;; expel john, he shouldn't exist in the school-records anymore
(defn q5 []
  (dissoc school-records 1001))

(q5)


;;;; map

;; use (map ...)

(def numbers [1 2 3 4 5])

;; double all the numbers
(defn q6 []
  (map + numbers numbers))

(q6)

;; convert all the numbers to string
(defn q7 []
  (map str numbers))
(q7)

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
(defn q8 []
  (defn give-title [person]
    (let  [title (if (= (:gender person) :male)
                   "Mister "
                   "Miss ")]
      (assoc person :name (str title (:name person)))))
  
  (map give-title people))

(q8)

;; using the job map (DO NOT HARD CODE VALUES WITHOUT USING THE MAP)
;; assign everyone their jobs in :job JOB_HERE
(defn q9 []
(defn give-job [person]
  (assoc person :job (get job (get person :name))))
  (map give-job people))

(q9)

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

(defn get-task [person]
  (-> person
      (get :tasks)
      first))
(defn get-function-from-task [person]
  (get task-outcomes (get-task person)))
(defn complete-task [person]
  (update person :tasks rest))
(defn do-tasks [person]
  (loop [target-person person]
        (let [task-function (get-function-from-task target-person)]
          (if task-function
            (recur ( -> target-person
                     (task-function)
                     (complete-task)))
            target-person))))
(def example-person (first people2))
(defn q10 []
  (map do-tasks people2))
(q10)