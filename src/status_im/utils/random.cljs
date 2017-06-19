(ns status-im.utils.random
  (:require [status-im.utils.modules :as modules]))

(defn timestamp []
  (.getTime (js/Date.)))
 
(def Chance (modules/require-js "chance"))

(def chance (Chance.))

(defn id []
  (str (timestamp) "-" (.guid chance)))
