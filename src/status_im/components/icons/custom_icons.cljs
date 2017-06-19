(ns status-im.components.icons.custom-icons
  (:require [reagent.core :as r]
            [status-im.utils.modules :as modules]))

(def ion-icon
  (r/adapt-react-class (.-default (modules/require-js "react-native-vector-icons/Ionicons"))))
