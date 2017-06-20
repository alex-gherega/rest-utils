(ns rest-utils.input
  (:require [clojure.repl :refer [doc]]))

(defn filter-nils []
  (filter #(-> % nil? not)))

(defn extract [m & names]
  (if-let [m m]
    (vec (keep m names))))
