(ns status-im.protocol.test.node
  (:require [cljs.test :refer-macros [deftest is]]
            [status-im.protocol.web3.utils :as u]
            [clojure.string :as s]))

;; dependencies
(def fs (js/require "fs"))
(def child-process (js/require "child_process"))
(def sleep (.-sleep (js/require "sleep")))

(def build-dir "target")

(def exist? (.-existsSync fs))

(defn exec-sync [command options]
  (.execSync child-process command (clj->js options)))

(defn exec [command options]
  (.exec child-process command (clj->js options)))

(defn spawn-sync [command args options]
  (.spawnSync child-process command (clj->js args) (clj->js options)))

(defn spawn [command args options]
  (.spawn child-process command (clj->js args) (clj->js options)))

(defonce node-process (atom nil))

(defn prepare-env! []
  (when-not (exist? build-dir)
    (println "mkdir " build-dir)
    (.mkdirSync fs build-dir))
  (let [dir  (s/join "/" [build-dir "status-go"])
        opts #js {:cwd dir}]
    (if-not (exist? dir)
      (exec-sync "git clone git@github.com:status-im/status-go.git -b bug/whisper-on-geth1.6.1" #js {:cwd build-dir})
      (exec-sync "git pull origin bug/whisper-on-geth1.6.1" opts))
    (println "Compile statusgo...")
    (exec-sync "make statusgo" opts)
    (println "Done.")))

(defn start! []
  (when-not @node-process
    (println "Start wnode...")
    (let [dir (s/join "/" [build-dir "status-go" "build" "bin"])]
      (let [proc (spawn "./statusd"
                        ["wnode" "--http" "--httpport" "8645"]
                        {:cwd dir})]
        (reset! node-process proc)
        (sleep 5)
        (println "Done.")))))


(defn stop! []
  (println "Stop wnode...")
  (.kill @node-process)
  (println "Done.")
  (reset! node-process nil))

(def identity-1 "0x04eedbaafd6adf4a9233a13e7b1c3c14461fffeba2e9054b8d456ce5f6ebeafadcbf3dce3716253fbc391277fa5a086b60b283daf61fb5b1f26895f456c2f31ae3")
(def identity-2 "0x0490161b00f2c47542d28c2e8908e77159b1720dccceb6393d7c001850122efc3b1709bcea490fd8f5634ba1a145aa0722d86b9330b0e39a8d493cb981fd459da2")
(def topic-1 "0xdeadbeef")

(def topic-2 "0xbeefdead")
