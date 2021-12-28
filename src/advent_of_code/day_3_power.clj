(ns advent-of-code.day-3-power
  (:require [advent-of-code.common :refer [scan]]))

(defn bit-to-data [letter & [cnt]]
  (+ (or cnt 0) (if (= letter "1") 1 0)))

(defn cons-data-from-string [value accum]
  (if (> (count value) 0)
    (let [start (- (count accum) (count value))
          letter-count (bit-to-data (subs value 0 1) (nth accum start))]
      (cons-data-from-string
        (subs value 1)
        (vec (concat
               (subvec accum 0 start)
               [letter-count]
               (subvec accum (inc start))))))
    accum))

(defn ge-calc-3a [data value]
  (let [{accum :accum
         total :total} (or data {:accum (vec (repeat (count value) 0))
                                 :total 0})]
    {:accum (cons-data-from-string value accum)
     :total (inc total)}))

(defn bits-to-number [bits]
  (reduce (fn [d v] (+ (* d 2) v)) 0 bits))

(defn power-consumption [data]
  (let [{accum :accum
         total :total} data
        avgs (map (fn [d] (/ d total)) accum)
        bits (map (fn [v] (if (>= v 0.5) 1 0)) avgs)
        gamma (bits-to-number bits)
        epsilon (bits-to-number (map (fn [v] (if (> v 0) 0 1)) bits))]
    (println bits)
    (printf "The final number is %d\n" (* gamma epsilon))))

;(defn ge-calc-3b [data value])
;  (let [{accum :accum}])
;         total :total (or data {:accum (vec (repeat (count value) 0))})
;                                 :total 0
;        row (vec (repeat (count value) 0))
;        row (cons-data-from-string value row)
;    {:accum (map (fn))}
;     :total (inc total)

(defn air-quality [data])

(scan "power.in" ge-calc-3a power-consumption)
;(scan "power.in" ge-calc-3b air-quality)
