(ns kata.play.runMachine
    (:import (kata.vending UnknownCoin ChangeInCoins CoinAccumulator CoinCount CoinDisplay
                           CoinExchanger CoinWeightFactory CoinWeights CoinWeightsImpl CoinsAccumulated
                           MachineDisplay MeasuredCoin Product ProductSelection ProductStatus UnknownCoin
                           VendingAction VendingButton VendingMachine VendingMachineStatus)))


(def machineFactory (CoinWeightFactory.))
(def coinIdMachine (.buildUsCoinSystem machineFactory))
(def status (VendingMachineStatus.))
(def uNickle (UnknownCoin. 5.0, 21.21))
(def uDime (UnknownCoin. 2.268, 17.92))
(def uQuarter (UnknownCoin. 5.670, 24.26))

(.getWeight uQuarter)
(.getDiameter uQuarter)




