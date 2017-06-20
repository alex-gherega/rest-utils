(ns rest-utils.coercers
  (:require [schema.core :as s]
            [schema.spec.core :as spec]
            [schema.coerce :as c]))

(defn json-coercer [schema]
  (c/coercer schema c/json-coercion-matcher))

(s/defn basic-coercer [schema
                       defaults :- {s/Keyword s/Any}]
  (spec/run-checker
   (fn [s params]
     (let [walk (spec/checker (s/spec s) params)]
       (fn [x]
         (if (and (map? s)
                  (map? x)
                  (not= (-> s keys count) (-> x keys count)))
           (walk (merge defaults x))
           (walk x)))))
   true
   schema))

(s/defn clean-coercer [schema
                       defaults :- [s/Keyword]]
  (spec/run-checker
   (fn [s params]
     (let [walk (spec/checker (s/spec s) params)]
       (fn [x]
         (if (and (map? s)
                  (map? x)
                  (not= (-> s keys count) (-> x keys count)))
           (walk (apply dissoc x defaults))
           (walk x)))))
   true
   schema))

(defn json-coerce [schema data]
  ((json-coercer schema) data))

(defn basic-coerce [schema defaults data]
  ((basic-coercer schema defaults) data))

(defn clean-coerce [schema defaults data]
  ((clean-coercer schema defaults) data))
