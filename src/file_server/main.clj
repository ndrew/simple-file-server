(ns file-server.main
  (:gen-class :main true)
  (:use [ring.adapter.jetty :only [run-jetty]])
  (:require [clojure.tools.cli :as cli]
            [clojure.java.browse :as browser]
            [file-server.handler :as server]))


(defn -main [& args]
  (let [[opts args banner]
        (cli/cli args
             ["-p" "--port" "Port to listen on"  :default 8899 :parse-fn #(Integer. %)]
             ["-a" "--[no-]auto-open"            :default true :flag true]
             ["-d" "--dir" "Directory to serve files from. If not provided directory with jar file." :default ""])]

    (println banner)
    
    (let [path (if (empty? (:dir opts)) (System/getProperty "user.dir")
                 (.getAbsolutePath (clojure.java.io/file (:dir opts))))]
                 (println (str "using path " path))
                 (print "starting..")
                 (future (run-jetty (server/get-app path) {:port (:port opts)}))
                 (println "\tok")
                 (if (:auto-open opts)
                   (browser/browse-url (str "http://localhost:" (:port opts)))))))                 
                 