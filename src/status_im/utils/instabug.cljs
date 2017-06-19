(ns status-im.utils.instabug
  (:require [taoensso.timbre :as log]
            [status-im.utils.modules :as modules]))

(def instabug-rn (modules/require-js "instabug-reactnative"))

(defn log [str]
  (if js/goog.DEBUG
    (log/debug str)
    (.IBGLog instabug-rn str)))

(defn instabug-appender []
  {:enabled?   true
   :async?     false
   :min-level  nil
   :rate-limit nil
   :output-fn  :inherit

   :fn         (fn [data]
                 (let [{:keys [level ?ns-str ?err output_]} data]
                   (log (force output_))))})

(when-not js/goog.DEBUG
  (log/merge-config! {:appenders {:instabug (instabug-appender)}}))
