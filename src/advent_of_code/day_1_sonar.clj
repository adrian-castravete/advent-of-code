(ns advent-of-code.day-1-sonar
  (:require [advent-of-code.common :refer [scan]]))

(defn info-increases [data]
  (printf "No. of increases: %d\n" (second data)))

(defn decide-1a [data value]
  (let [value (Integer/parseInt value)]
    (if data
      (let [[old incs] data
            msg (cond (< value old) "decreased"
                      (> value old) "increased"
                      :else "no change")]
        (printf "%d (%s)\n" value msg)
        [value (+ incs (if (= msg "increased") 1 0))])
      (do
        (printf "%d (N/A - first value)\n" value)
        [value 0]))))

(defn decide-1b [data value]
  (let [value (Integer/parseInt value)]
    (if data
      (let [[track incs] data
            latest (:latest track)
            new-latest (conj latest value)
            next-latest (if (> (count new-latest) 3)
                          (subvec new-latest 1)
                          new-latest)
            old-sum (:sum track)
            new-sum (reduce + next-latest)]
        [{:latest next-latest
          :sum new-sum} (+ incs (if (and
                                      (= (count next-latest) 3)
                                      (> new-sum old-sum)) 1 0))])
      [{:latest [value] :sum value} -1])))


(scan "sonar.in" decide-1a info-increases)
(scan "sonar.in" decide-1b info-increases)
