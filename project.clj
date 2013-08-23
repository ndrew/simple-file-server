(defproject file-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring-json-response "0.2.0"]
                 [ring/ring-jetty-adapter "1.2.0"]
                 [org.clojure/tools.cli "0.2.2"]
                 ]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler file-server.handler/app}
  
  :main file-server.main
  
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}
   :deploy {:main ^{:skip-aot false} file-server.main :aot [file-server.main]}
   })
