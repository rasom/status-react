(ns status-im.components.webview-bridge
  (:require [reagent.core :as r]
            [status-im.utils.modules :as modules]))

(def webview-bridge-class
  (r/adapt-react-class (.-default (modules/require-js "react-native-webview-bridge"))))

(defn webview-bridge [opts]
  [webview-bridge-class opts])
