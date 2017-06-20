(ns rest-utils.muservices
  (:require [clojurewerkz.mailer.core :refer [delivery-mode! with-settings with-defaults with-delivery-mode build-email deliver-email]]))

;; define the email function

;; NOTE: for gmail one needs to turn-off: https://www.google.com/settings/security/lesssecureapps

(defn send-email

  [auth-credentials to from bcc subject [t-file t-map]]
   ;; set default delivery mode (:smtp, :sendmail or :test)

  (with-settings auth-credentials
    (with-delivery-mode :smtp
      (deliver-email {:from from :to to :bcc bcc :subject subject}
                     t-file t-map))))

(defn pay-up [])

