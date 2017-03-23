(ns rest-utils.core
  (:require [rest-utils.input :as in]))

(defn serve [service-fn
             parameter-names
             request]
  (apply service-fn (in/extract paramter-names request)))
