(defproject claigslist "0.1.0-SNAPSHOT"
  :description "search craigslist"
  :url "https://github.com/mark-nery/claigslist"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-http "0.7.8"]
                 [hickory "0.5.1"]]
  :main ^:skip-aot claigslist.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
