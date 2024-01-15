(ns clojure-training-2024-extracurricular.extra02)

(def library-catalog
  [{:title "The Catcher in the Rye",
    :author "J.D. Salinger",
    :genre "Fiction",
    :publication-year 1951,
    :copies [{:copy-number 1, :status :available}
             {:copy-number 2, :status :checked-out, :due-date "2024-02-01"}]}
   {:title "To Kill a Mockingbird",
    :author "Harper Lee",
    :genre "Fiction",
    :publication-year 1960,
    :copies [{:copy-number 1, :status :checked-out, :due-date "2024-01-20"}],
    :reviews [{:user "BookLover123",
               :rating 5,
               :comment "Great book, highly recommended!"}
              {:user "Reader456",
               :rating 4,
               :comment "Enjoyed the characters and the plot."}
              {:user "PotterFanatic",
               :rating 1,
               :comment "This isn't Harry Potter, boo"}]}
   {:title "1984",
    :author "George Orwell",
    :genre "Dystopian Fiction",
    :publication-year 1949,
    :copies [{:copy-number 1, :status :available}
             {:copy-number 2, :status :available}
             {:copy-number 3, :status :checked-out, :due-date "2024-02-10"}]}
   {:title "The Great Gatsby",
    :author "F. Scott Fitzgerald",
    :genre "Classic",
    :publication-year 1925,
    :copies [{:copy-number 1, :status :available}
             {:copy-number 2, :status :available}]}
   {:title "Harry Potter and the Sorcerer's Stone",
    :author "J.K. Rowling",
    :genre "Fantasy",
    :publication-year 1997,
    :copies [{:copy-number 1, :status :available}
             {:copy-number 2, :status :checked-out, :due-date "2024-01-25"}
             {:copy-number 3, :status :available}],
    :reviews [{:user "PotterFanatic",
               :rating 5,
               :comment "Love the magical world and the characters!"}
              {:user "WizardReader",
               :rating 4,
               :comment "An enchanting start to the series."}]}])

;; README:
;; All collections should be vectors -> []
;; Some functions might convert them to lists -> ()
;; In that case call (vec YOUR-COLL-HERE) to convert them back to vectors

;; !!! Evaluate the extra02_test.clj before running or it wont find the tests !!!

;; Fill in all functions except fiction?
;; Test by running tests for current Namespace
;; You can individually call functions using library-catalog variable (defined above)

(defn fiction? [book]
  (= (:genre book)
     "Fiction"))

(defn find-all-fiction-books [catalogue]
  ;; This is how you do it with filter, but I want you to do it with loop
  (filter fiction? catalogue)

  "YOUR ANSWER HERE")

(defn add-1000-to-all-publication-years [catalogue])

(def review-to-add {:user "AI REVIEWER",
                    :rating 1,
                    :comment "Review Bombed."})

;; use review-to-add as the review to add
;; all :reviews should be kept as vectors [...]
(defn add-review-to-all-books [catalogue])

(defn remove-all-potter-fanatic-reviews [catalogue])

;; book = any entry in catalogue
;; return an empty vector [] if no copies available
(defn get-available-copies-for-book [book])

;; Return all available copies over all books.
;; When testing, return will be converted to a set so it will be order independed.
;; Each entry should also contain :book-name TITLE-OF-BOOK
;; e.g. {:book-name "foo" :copy-number 2, :status :available}
;; Look into functions such as flatten / concat (they may be useful)
(defn get-all-available-copies [catalogue])