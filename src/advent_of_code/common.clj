(ns advent-of-code.common
  (:require [clojure.java.io :as io]))

(defn scan [file-in-name red-fn print-fn]
  (with-open [rdr (io/reader file-in-name)]
    (let [out (reduce red-fn nil (line-seq rdr))]
      (print-fn out))))
