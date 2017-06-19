(ns status-im.utils.identicon
  (:require [status-im.utils.modules :as modules]))

(def default-size 40)

(def identicon-js (modules/require-js "identicon.js"))

(defn identicon
  ([hash] (identicon hash default-size))
  ([hash options]
    (str "data:image/png;base64," (str (new identicon-js hash options)))))

