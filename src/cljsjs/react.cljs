(ns cljsjs.react
  (:require [status-im.utils.modules :as modules]))

(when (exists? js/window)
  (set! js/ReactNative (modules/require-js "react-native")))
