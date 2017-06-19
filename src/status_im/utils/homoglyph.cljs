(ns status-im.utils.homoglyph
  (:require [status-im.utils.modules :as modules]))

(def homoglyph-finder (modules/require-js "homoglyph-finder"))

(defn matches [s1 s2]
  (.isMatches homoglyph-finder s1 s2))
