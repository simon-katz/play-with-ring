(ns play-with-ring.server
  (:require [ring.adapter.jetty :as jetty]
            [clojure.pprint :as pp]))

(def port 4000)

(defn create-and-start
  [handler]
  (try
    (jetty/run-jetty handler
                     {:port port
                      :join? false})
    (catch java.net.BindException e
      (throw (Exception.
              (pp/cl-format nil
                            "Failed to run Jetty on port ~A.~@
                             ~A"
                            port e))))))

(defn stop
  [server]
  (.stop server))
