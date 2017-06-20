(ns rest-utils.general)

(defmacro pack-args [& args] `(zipmap (map keyword '~args) (vector~@args)))
