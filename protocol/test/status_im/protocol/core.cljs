(ns status-im.protocol.test.core
  (:require [cljs.test :refer-macros [deftest is testing run-tests async]]
            [cljs.nodejs :as nodejs]
            [status-im.protocol.test.node :as node]
            [status-im.protocol.core :as protocol]))

(nodejs/enable-util-print!)

(println "Start testing")

(node/prepare-env!)
(node/start!)

(def rpc-url "http://localhost:8645")
(def Web3 (js/require "web3"))
(defn make-web3 []
  (Web3. (Web3.providers.HttpProvider. rpc-url)))

(defn make-callback [identity]
  (fn [& args]
    (.log js/console (str :callback " " identity "\n" args))))

(defn post-error-callback [identity]
  (fn [& args]
    (.log js/console (str :post-error " " identity "\n" args))))

(defn id-specific-config
  [id {:keys [private public]} contacts]
  {:identity            id
   :callback            (make-callback id)
   :profile-keypair     {:public  public
                         :private private}
   :contacts            contacts
   :post-error-callback (post-error-callback id)})

(deftest testt
  (let [web3          (make-web3)
        id1-keypair   (protocol/new-keypair!)
        id2-keypair   (protocol/new-keypair!)
        common-config {:web3                        web3
                       :groups                      []
                       :ack-not-received-s-interval 125
                       :default-ttl                 120
                       :send-online-s-interval      180
                       :ttl-config                  {:public-group-message 2400}
                       :max-attempts-number         3
                       :delivery-loop-ms-interval   500
                       :hashtags                    []
                       :pending-messages            []}
        id1-config (id-specific-config node/identity-1 id1-keypair [])
        id2-config (id-specific-config node/identity-2 id2-keypair [])
        ]
    (async done
      (.log js/console "WOW")
      (protocol/init-whisper! (merge common-config id1-config))
      (node/sleep 5)
      (done))
    #_(protocol/init-whisper! (merge common-config id2-config)))
  (is (= 1 1)))

(run-tests)

#_(node/stop!)
