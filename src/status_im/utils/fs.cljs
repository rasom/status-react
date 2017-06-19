(ns status-im.utils.fs
  (:require [status-im.utils.modules :as modules]))

(def fs (modules/require-js "react-native-fs"))

(defn move-file [src dst handler]
  (-> (.moveFile fs src dst)
      (.then #(handler nil %))
      (.catch #(handler % nil))))

(defn read-file [path encoding on-read on-error]
  (-> (.readFile fs path encoding)
      (.then on-read)
      (.catch on-error)))
