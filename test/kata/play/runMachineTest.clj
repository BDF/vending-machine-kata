(ns kata.play.runMachineTest
    (:require
      [kata.play.vendingMachine :as vm]
      [clojure.test :refer :all]))


(testing "adds unknown quarter and gets quarter back"
         (let [vms (vm/addCoin vm/vms vm/uQuarter)]
              (is (= 1 (-> vms (.getCoinsAccumulated) (.getCoinCount))))
              (is (-> vms
                      (.getCoinsAccumulated)
                      (.getRawCoins)
                      (.contains vm/quarter)))
              )
         )
(testing "adds unknown dime and gets dime back"
         (let [vms (vm/addCoin vm/vms vm/uDime)]
              (is (= 1 (-> vms (.getCoinsAccumulated) (.getCoinCount))))
              (is (-> vms
                      (.getCoinsAccumulated)
                      (.getRawCoins)
                      (.contains vm/dime)))
              )
         )

(testing "Adding money accumulates in the coin accumaltor"
         )


;(require '[clojure.test :refer [run-tests]])
;(require 'kata.play.runMachineTest :reload-all)
;(run-tests 'kata.play.runMachineTest)