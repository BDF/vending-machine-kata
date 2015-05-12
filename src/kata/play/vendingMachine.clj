(ns kata.play.vendingMachine
    (:import (kata.vending UnknownCoin ChangeInCoins CoinAccumulator CoinCount CoinDisplay
                           CoinExchanger CoinWeightFactory CoinWeights CoinWeightsImpl CoinsAccumulated
                           MachineDisplay MeasuredCoin Product ProductSelection ProductStatus UnknownCoin
                           VendingAction VendingButton VendingMachine VendingMachineStatus)))

(def machineFactory (CoinWeightFactory.))
(def coinIdMachine (.buildUsCoinSystem machineFactory))

;; Should just have one set of configuration; instead of one for clojure and one for java.
;; but part of this is to play with the java interop...
(def uNickle (UnknownCoin. 5.0, 21.21))
(def uDime (UnknownCoin. 2.268, 17.92))
(def uQuarter (UnknownCoin. 5.670, 24.26))

(def nickle (MeasuredCoin. 5))
(def dime (MeasuredCoin. 10))
(def quarter (MeasuredCoin. 25))


(def cola (Product. "cola" 100))
(def chips (Product. "chips" 50))
(def candy (Product. "candy" 65))

(def initialCoinCount (doto (new java.util.HashMap)
                            (.put dime 5)
                            (.put nickle 5)
                            (.put quarter 5)))

(def initialProductCount (doto (new java.util.HashMap)
                            (.put cola 5)
                            (.put chips 5)
                            (.put candy 5)))

(def vms (VendingMachineStatus.))
(def coinExchanger (CoinExchanger. initialCoinCount))
(def productStatus (ProductStatus. initialProductCount))
(.getWeight uQuarter)
(.getDiameter uQuarter)

(def vendingMachine (VendingMachine. coinIdMachine coinExchanger productStatus))

(defn addCoin [vms unkCoin]
      (.addCoin vendingMachine vms unkCoin))

(defn addQuarter [vms]
      (addCoin vms uQuarter))

(defn addDime [vms]
      (addCoin vms uDime))

(defn addNickle [vms ]
      (addCoin vms uNickle))

(defn selectProductButton [vms product]
      (let [vb (VendingButton. VendingAction/VEND product)]
           (VendingMachineStatus. (.getCoinsAccumulated vms)
                                 (.getMachineDisplay vms)
                                 vb)))

(defn returnCoins [vms]
      (let [vb (VendingButton. VendingAction/COIN_RETURN Product/NO_PRODUCT_SELECTED)]
           (VendingMachineStatus. (.getCoinsAccumulated vms)
                                 (.getMachineDisplay vms)
                                 vb))
      (.selectButton vendingMachine vms))

(defn vendProduct [vms product]
      (.selectButton vendingMachine (selectProductButton vms product)))

(defn coinDisplay [vms]
      (.getCoinDisplay vendingMachine vms))


(defn takeAction[action vms]
      (case action
            "q" (addQuarter vms)
            "d" (addDime vms)
            "n" (addNickle vms)
            "c" (vendProduct vms cola)
            "h" (vendProduct vms chips)
            "a" (vendProduct vms candy)
            "r" (returnCoins vms)
            "e" nil))


(defn runMachine []
  (println (str ";; q -> addQuarter
;; n -> addNickle
;; d -> addDime
;; c -> vend cola
;; h -> vend chips
;; a -> vend candy
;; e -> quit
;; r -> return coins"))
  (let [vms (atom (VendingMachineStatus.))]
       (while (not (nil? @vms)))
        (let [action (read-line)]
             (println  (str "aciton? :"))
             (reset! vms (takeAction vms action))
             (print (-> (.getCoinDisplay vendingMachine) (.getCoinDisplay)) ))

       )
  )




