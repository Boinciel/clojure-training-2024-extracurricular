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
#_(defn q2 []
    (defn salary-increase [record raise]
      (* (get record :salary) raise))

    (defn update-position [record]
      (assoc record :type :principle :salary (int (salary-increase record 1.2))))

    (update school-records 2003 update-position))

(defn q2 []
  (defn promote-to-principle [record]
    (-> record
        (assoc :type :principle)
        (update :salary * 1.2)
        (update :salary int)))

  (update school-records 2003 promote-to-principle))


;; John should also do the class :science
(defn q3 []
  (update-in school-records [1001 :classes] conj :science))
  ;; (defn take-class [record student class]
    ;; (update-in record [student :classes] 
    ;;           (conj (get-in record [student :classes])class)))
  ;; (take-class school-records 1001 :science))


;; (let [name     "john na"
;;       some-val (+ 1 2 (* 3 4))]
;;   {:name name :val some-val})

;; Students should have a new entry in their record :total-grade TOTAL-GRADE
(defn q4 []

  (defn sum-coll [coll]
    (apply + coll))
  (defn sum-grades [target]
    (sum-coll (-> target
                  (second)
                  (get :grades))))
  (-> school-records
      (assoc-in [1001 :total-grade] (sum-grades (first school-records)))
      (assoc-in [1002 :total-grade] (sum-grades (second school-records)))))



;; (map assign-student-total-grade school-records)

;; (defn student? [record]
;;   (= :student (get (second record) :type)))

;; (defn assign-student-total-grade [record] 
;;   (if (student? record)
;;     ;; If it's a student we need to total the grade
;;     (let [student-id     (first record)
;;           student-record (second record)
;;           total-grade    (sum-coll (get student-record :grades))
;;           updated-record (assoc student-record :total-grade total-grade)]
;;       [student-id updated-record])
;;     ;; Otherwise return the record unmodified
;;     record))

;;                                   [1001 {:name ... :type ...}]
;; (defn assign-student-total-grade [[id {:keys [type grades] :as record} :as id-record-pair]]
;;   (if (= type :student)
;;     [id (assoc record :total-grade (sum-coll grades))]
;;     id-record-pair))

;;   (let    [target        school-records
;;            filter        :students
;;            ID            (map identity school-records)
;;            ])
;; (defn get-students-only [target ID filter]

;;    (if (= ((-> example-record
;;                (second)
;;                (get :type))) filter)
;;      (update school-records conj target)
;;      ()))

;;  (map (update-in school-records [get-students-only] conj :total-grade "TOTAL-GRADE") school-records))



;;   (defn add-entry [target filter entry value]

;;     (if (= (get target :type) filter)
;;       (update school-records target entry value)
;;       ()))

;;  (update-in school-records  add-entry :student :total-grade "TOTAL-GRADE"))

(q4) ; come back to this later


;; expel john, he shouldn't exist in the school-records anymore
(defn q5 []
  (defn expel-student [id]
    (dissoc school-records id))

 ; (expel-student school-records 1001))
  (expel-student 1001))


(q5)

;;;; map

;; use (map ...)

(def numbers [1 2 3 4 5])

;; double all the numbers
(defn q6 []
  ;; the easy way that gets the job done fast but i tried and failed to overcomplicate it
  (map + numbers numbers))

  ;; (loop [x (count numbers)  out []]

  ;;   (if x
  ;;     ( recur (rest x) (conj out (* (first numbers) 2)))
  ;;     out)))



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
  (defn associate-title [person]
    (let [title (if (= (:gender person) :male)
                  "Mister "
                  "Miss ")]
      (assoc person :name (str title (:name person)))))

  (map associate-title people))


#_(q8
   (map merge))

(defn associate-job [person]
  (let [jobb (get job (get person :name) "UNKNOWN")]
    (assoc person :job jobb)))


;; using the job map (DO NOT HARD CODE VALUES WITHOUT USING THE MAP)
;; assign everyone their jobs in :job JOB_HERE
(defn q9 []
  (map associate-job people))

(q9)
  (defn sum-coll [coll]
  (apply + coll))




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

(def character-example (first people2)) ; temp
(defn get-task-function [character]
  (get task-outcomes (-> character
                         (get :tasks)
                         (first))))
(defn eliminate-task [character]
  (update character :tasks (comp vec rest))

 #_ (assoc character :tasks (rest (-> (character)
                                         (get :tasks)))))
(defn do-character-tasks [character]
  (loop [character-being character]
    (let [character-task (get-task-function character-being)]
      (if character-task
        (recur (->  character-being
                    (character-task)
                    (eliminate-task)))
        character-being))))

;; (defn do-character-tasks [character]
;;   (loop [character-being character
;;          have-task false]
;;     (println "loop")
;;     (if (have-task false) 
;;       (if (get-task-function character-being)
;;         (recur (-> character-being
;;                    (get-task-function)
;;                    ) (have-task true))
;;         character-being))
;;     (if (have-task true)
;;       (recur (-> character-being
;;                  (eliminate-task))
;;              (have-task false))
;;       )))


(get-task-function character-example)
(eliminate-task character-example)
(do-character-tasks character-example)




(defn q10 []
  (map do-character-tasks people2))

(q1)
(q2)
(q3)
(q4)
(q5)
(q6)
(q7)
(q8)
(q9)
(q10)