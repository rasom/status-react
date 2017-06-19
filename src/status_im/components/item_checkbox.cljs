(ns status-im.components.item-checkbox
  (:require [reagent.core :as r]
            [status-im.utils.modules :as modules]))

(def item-checkbox (r/adapt-react-class (modules/require-js "react-native-circle-checkbox")))

