(ns rest-utils.mungers
  (:require [schema.core :as s]
            [rest-utils.schemas :as schemas]
            [rest-utils.coercers :as coercers]))

(def defaults
  {:contactus {}
   :membership {}
   :clean (keys {:sharesshares s/Str ;; TODO: cleanup before sendin?
                 :__anti-forgery-token s/Any ;; TODO: cleanup in ui before sending
                 })
   :default {s/Keyword s/Any}})

(defn- munge-coercion [coerce-fn schema request-data]
  (coerce-fn schema
             (-> request-data first key defaults)
             (-> request-data first val)))

(defmulti munge! (fn [x] (-> x first key)))

(defmethod munge! :clean [request-data]
  (munge-coercion coercers/clean-coerce
                  {s/Keyword s/Any}
                  request-data))

(defmethod munge! :contactus [request-data]
  (munge-coercion coercers/basic-coerce
                  schemas/contactus
                  request-data))

(defmethod munge! :membership [request-data]
  (munge-coercion coercers/basic-coerce
                  schemas/membership
                  request-data))

(defmethod munge! :default [request-data]
  (coercers/json-coerce {s/Keyword s/Any}
                        (-> request-data first val)))
