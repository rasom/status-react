(ns status-im.components.invertible-scroll-view
  (:require [reagent.core :as r]
            [status-im.utils.modules :as modules]))

(def class (modules/require-js "react-native-invertible-scroll-view"))

(defn invertible-scroll-view [props]
  (r/create-element class (clj->js (merge {:inverted true} props))))

