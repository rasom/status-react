(ns status-im.utils.modules)

(defonce modules (atom {}))

(defn require-js [module-name]
  (if (and (exists? js/window) (exists? js/require))
    (if (contains? @modules module-name)
      (get @modules module-name)
      (let [module (js/require module-name)]
        (swap! modules assoc module-name module)
        module))
    #js {}))
