(ns status-im.components.native-action-button
  (:require [reagent.core :as r]
            [status-im.utils.modules :as modules]))

(def class (modules/require-js "react-native-action-button"))

(def native-action-button (r/adapt-react-class (.-default class)))
(def native-action-button-item (r/adapt-react-class (.. class -default -Item)))
