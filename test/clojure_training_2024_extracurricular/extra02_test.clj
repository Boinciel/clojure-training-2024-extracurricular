(ns clojure-training-2024-extracurricular.extra02-test
  (:require [clojure.test :refer :all]
            [clojure-training-2024-extracurricular.extra02 :refer :all]))

(declare find-all-fiction-books* remove-all-potter-fanatic-reviews* add-review-to-all-books*
         add-1000-to-all-publication-years* get-available-copies-for-book*
         get-all-available-copies*)

(deftest tests
  (testing
   (testing "find-all-fiction-book"
     (is (= (seq (find-all-fiction-books* library-catalog))
            (seq (find-all-fiction-books library-catalog)))))
    (testing "add-1000-to-all-publication-years"
      (is (= (seq (add-1000-to-all-publication-years* library-catalog))
             (seq (add-1000-to-all-publication-years library-catalog)))))
    (testing "add-review-to-all-books"
      (is (= (seq (add-review-to-all-books* library-catalog))
             (seq (add-review-to-all-books library-catalog)))))
    (testing "remove-all-potter-fanatic-reviews"
      (is (= (seq (remove-all-potter-fanatic-reviews* library-catalog))
             (seq (remove-all-potter-fanatic-reviews library-catalog)))))
    (testing "get-available-copies-for-book (has copies)"
      (is (= (seq (get-available-copies-for-book* (first library-catalog)))
             (seq (get-available-copies-for-book (first library-catalog))))))
    (testing "get-available-copies-for-book (has no copies)"
      (is (= (vec (get-available-copies-for-book* (second library-catalog)))
             (get-available-copies-for-book (second library-catalog)))))
    (testing "get-all-available-copies"
      (is (= (set (get-all-available-copies* library-catalog))
             (set (get-all-available-copies library-catalog)))))))

;;;; DO NOT LOOK DOWN HERE, ANSWERS
;;
;;
;;
;;
;;
;;
;;
;;
;;;; DO NOT LOOK DOWN HERE, ANSWERS
;;
;;
;;
;;
;;
;;
;;
;;
;;;; DO NOT LOOK DOWN HERE, ANSWERS
;;
;;
;;
;;
;;
;;
;;
;;
;;;; DO NOT LOOK DOWN HERE, ANSWERS
;;
;;
;;
;;
;;
;;
;;
;;
;;;; DO NOT LOOK DOWN HERE, ANSWERS


(defn- find-all-fiction-books* [catalogue]
  ;; This is how you do it with filter, but I want you to do it with loop
  (filter fiction? catalogue))

(defn add-1000-to-all-publication-years* [catalogue]
  (map #(update % :publication-year + 1000) catalogue))

(defn add-review-to-all-books* [catalogue]

  (defn add-review-to-book* [{:keys [reviews] :as book}]
    (if reviews
      (conj reviews review-to-add)
      (assoc book :reviews [review-to-add])))

  (map add-review-to-book* catalogue))

(defn remove-all-potter-fanatic-reviews* [catalogue]

  (defn remove-potter-fanatic-review* [reviews]
    (vec (remove #(= "PotterFanatic" (:user %)) reviews)))

  (defn remove-potter-fanatic-review-if-exists* [{:keys [reviews] :as book}]
    (if reviews
      (update book :reviews remove-potter-fanatic-review*)
      book))

  (map remove-potter-fanatic-review-if-exists* catalogue))

(defn get-available-copies-for-book* [{:keys [copies]}]
  (vec (filter #(= :available (:status %)) copies)))

(defn get-all-available-copies* [catalogue]

  (defn assoc-book-name* [book title]
    (assoc book :book-name title))

  (loop [remaining-books  catalogue
         available-copies []]
    (if (seq remaining-books)
      (let [curr-book (first remaining-books)
            title     (:title curr-book)]
        (recur (rest remaining-books)
               (concat available-copies
                       (->> curr-book
                            (get-available-copies-for-book*)
                            (map #(assoc-book-name* % title))))))
      available-copies)))