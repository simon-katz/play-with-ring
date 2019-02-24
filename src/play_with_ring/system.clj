(ns play-with-ring.system
  (:require [play-with-ring.server :as server]
            [play-with-ring.ring-play :as ring-play]))

;;;; ___________________________________________________________________________

(defn init
  "Returns a new instance of the whole application."
  []
  {:handler ring-play/handler})

;;;; ___________________________________________________________________________

(defn start-app
  [system]
  (let [handler (:handler system)
        server (server/create-and-start handler)]
    (into system
          {:app-server server})))

(defn stop-app
  [system]
  (when (:app-server system)
    (server/stop (:app-server system)))
  (dissoc system :app-server))

;;;; ___________________________________________________________________________

(defn start
  "Performs side effects to initialize the system, acquire resources,
  and start it running. Returns an updated instance of the system."
  [system]
  (-> system
      start-app))

(defn stop
  "Performs side effects to shut down the system and release its
  resources. Returns an updated instance of the system."
  [system]
  (-> system
      stop-app))

;;;; ___________________________________________________________________________

(defn -main []
  (let [system (init)]
    (start system)))
