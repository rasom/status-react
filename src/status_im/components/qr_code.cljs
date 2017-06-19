(ns status-im.components.qr-code
  (:require [reagent.core :as r]
            [status-im.utils.modules :as modules]))

(def class (modules/require-js "react-native-qrcode"))

(defn qr-code [props]
  (r/create-element
    class
    (clj->js (merge {:inverted true} props))))
