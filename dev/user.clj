(ns user
  "Namespace to support hacking at the REPL."
  (:require [play-with-ring.system :as system]
            [clojure.tools.namespace.repl :refer :all]))

;;;; ___________________________________________________________________________
;;;; Clojure workflow.
;;;; See:
;;;; - http://blogish.nomistech.com/clojure/clojure-workflow-demo/
;;;; - http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded

(defonce the-system
  ;; "A container for the current instance of the application.
  ;; Only used for interactive development."
  ;; 
  ;; Don't want to lose this value if this file is recompiled (when
  ;; changes are made to the useful additional utilities for the REPL
  ;; at the end of the file), so use `defonce`.
  ;; But note that this /is/ blatted when a `reset` is done.
  nil)

(defn init
  "Creates a system and makes it the current development system."
  []
  (alter-var-root #'the-system
                  (constantly (system/init))))

(defn start
  "Starts the current development system."
  []
  (alter-var-root #'the-system system/start))

(defn stop
  "Shuts down and destroys the current development system."
  []
  (alter-var-root #'the-system
                  (fn [s] (when s (system/stop s)))))

(defn go
  "Creates a system, makes it the current development system and starts it."
  []
  (init)
  (start))

(defn reset "Stop, refresh and go."
  []
  (stop)
  (refresh :after 'user/go))

;;;; ___________________________________________________________________________
;;;; App-specific additional utilities for the REPL
