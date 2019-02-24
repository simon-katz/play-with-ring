(defproject play-with-ring.ring-play "0.1.0-SNAPSHOT"
  :description "Play with Ring"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [ring "1.2.2"]
                 [ring/ring-json "0.3.1"]
                 [clojure-ring-bootstrap "0.2.0"]]
  :main play-with-ring.system
  :repl-options {:init-ns user}
  :profiles
  {:dev {:source-paths ["dev"]
         :dependencies [[org.clojure/tools.namespace "0.2.4"]
                        [midje "1.6.3"]
                        [clj-webdriver "0.6.0"]]
         :plugins [[lein-midje "3.1.3"]]}})
