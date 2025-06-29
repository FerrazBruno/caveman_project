(ns caveman.system
  (:require [caveman.routes :as routes]
            [ring.adapter.jetty :as jetty])
  (:import [org.eclipse.jetty.server Server]))

(set! *warn-on-reflection* true)

(defn start-server [system]
  (jetty/run-jetty
   (partial #'routes/route-handler system)
   {:port 9999
    :join? false}))

(defn stop-server [server]
  (Server/.stop server))

(defn start-system []
  (let [system-so-far {}]
    {::server (start-server system-so-far)}))

(defn stop-system [server]
  (stop-server (::server server)))
