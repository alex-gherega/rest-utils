(ns rest-utils.input)

(defn filter-nils []
  (filter #(-> % nil? not)))

(defn extract [m & names]
  (vec (keep m names)))
