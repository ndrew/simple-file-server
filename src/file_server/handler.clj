(ns file-server.handler
  (:use compojure.core
        ring.util.json-response)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.tools.cli :as cli]
            [clojure.java.browse :as browser]
            ))

(def ^:dynamic *document-root* (System/getProperty "user.dir"))

(defn get-xml-files[path]
  (let [directory (clojure.java.io/file path)
        files (filter (fn[f] 
                        (.endsWith (clojure.string/lower-case (.getName f)) ".xml"))
                      (file-seq directory))]
    (doall 
      (map (fn[f] 
             (.replaceAll 
               (.getPath f)
               path
               "")
             ) files)
      )))


; for lein ring server
(defroutes app-routes
  (GET "/get-xml-files" [] 
       (json-response (get-xml-files *document-root*)))
  (route/files "/" {:root *document-root*})
  (route/not-found "Not Found"))

; for lein ring server 
(def app
  (handler/site app-routes))


(defn get-app[path]
    (defroutes app-routes
        (GET "/get-xml-files" [] 
            (json-response (get-xml-files path)))
    (route/files "/" {:root path})
    (route/not-found "Not Found"))
  
    (handler/site app-routes))