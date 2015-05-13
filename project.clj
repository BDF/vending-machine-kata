(defproject vending-machine-kata "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot kata.play.core
  :target-path "target/%s"
  :java-source-paths ["src/kata/vending"]
  :profiles {:uberjar {:aot :all}})
