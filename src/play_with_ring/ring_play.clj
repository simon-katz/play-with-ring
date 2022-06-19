(ns play-with-ring.ring-play
  (:require [compojure.core :refer [HEAD GET PUT POST routes defroutes]]
            [compojure.route :refer [not-found]]
            [compojure.handler]
            [ring.middleware.content-type :as content-type]
            [ring.middleware.json :as ring-json]
            [ring.util.response :as response]
            [metrics.counters :as counters]))

(defroutes basic-stuff
  (GET "/" request
    "Welcome to Play with Ring")

  (POST "/" request
    ;; (println (pr-str request))
    ;; (str "POST Welcome to Play with Ring")
    ;; {:body {:the-request-is (pr-str request)}}
    (let [headers (-> request :headers)
          body (-> request :body slurp)]
      (println "#### [headers body] =" [(pr-str headers)
                                        (pr-str body)])
      (pr-str [body
               request])))

  (GET "/hello" request
    ;; TODO: How to convert Clojure to JSON?
    "Hello"))

(defroutes json-stuff*
  (GET "/this-request" request
    (case 2
      1 (response/response request)
      2 (response/response (dissoc request :body))))
  (GET "/f" request
    ;; TODO: How to convert Clojure to JSON?
    ;; (fn [& args] request)
    ;; (not-found "Sorry, there's nothing here.")
    ;; request
    (response/response {:a 1 :b 2 :c 3})))

(def json-stuff
  (-> (->> #'json-stuff*
           (compojure.core/context "/json" []))
      ring-json/wrap-json-response))

(def all-stuff
  (routes #'basic-stuff
          #'json-stuff
          ;; (not-found "Sorry, there's nothing here.")
          ))

(def handler
  (-> #'all-stuff 
      ;; compojure.handler/api
      ;; request-counting-middleware
      ))
