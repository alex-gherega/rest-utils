(ns rest-utils.schemas
  (:require [schema.core :as s]
            [schema.spec.core :as spec]))

(s/defschema contactus
  {:email s/Str
   :username s/Str
   :address s/Str
   :mobile s/Str
   :postcode s/Str})

(s/defschema membership {:member-email s/Str
                         :member-name s/Str
                         (s/optional-key :member-addr) s/Str
                         :member-mobile s/Str
                         :member-postcode s/Str
                         :member-co s/Str})
