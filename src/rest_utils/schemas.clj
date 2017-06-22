(ns rest-utils.schemas
  (:require [schema.core :as s]
            [schema.spec.core :as spec]))

(s/defschema user
  {:email s/Str
   :name s/Str
   :address s/Str
   :mobile s/Str})

(s/defschema contactus
  (merge user {:postcode s/Str}))

(s/defschema membership
  (merge user {:postcode s/Str
               :company s/Str}))
