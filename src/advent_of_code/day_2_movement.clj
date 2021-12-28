(ns advent-of-code.day-2-movement
  (:require [clojure.string :as str]
            [advent-of-code.common :refer [scan]]))

(defn move-2a [data value]
  (let [[x y] data
        x (if-not x 0 x)
        y (if-not y 0 y)
        [dir v] (str/split value #" ")
        dir (keyword dir)
        v (Integer/parseInt v)]
    (printf "%d %d (%s %d)\n" x y dir v)
    [(if (= dir :forward) (+ x v) x)
     (if (= dir :up)
       (- y v)
       (if (= dir :down)
         (+ y v)
         y))]))
;(m/match dir
;  :forward [(+ x v) y]
;  :up [x (- y v)]
;  :down [x (+ y v)])))

(defn move-2b [data value]
  (let [[x y aim] data
        x (if-not x 0 x)
        y (if-not y 0 y)
        aim (if-not aim 0 aim)
        [dir v] (str/split value #" ")
        dir (keyword dir)
        v (Integer/parseInt v)]
    (printf "%d %d %d (%s %d)\n" x y aim dir v)
    (cond
      (= dir :forward) [(+ x v) (+ y (* v aim)) aim]
      (= dir :up) [x y (- aim v)]
      (= dir :down) [x y (+ aim v)])))
;(m/match dir
;  :forward [(+ x v) y]
;  :up [x (- y v)]
;  :down [x (+ y v)])))

(defn final-movement [data]
  (printf "Final movement: %d\n" (reduce * data)))

(defn final-movement-no-aim [data]
  (let [[x y aim] data]
    (printf "Final movement (without aim): %d\n" (* x y))))

(scan "movement.in" move-2a final-movement)
(scan "movement.in" move-2b final-movement-no-aim)
