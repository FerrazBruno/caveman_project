(ns caveman.goodbye.routes
  (:require [hiccup2.core :as hiccup]))

(defn goodbye-handler [_system _requres]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str (hiccup/html [:html [:body [:h1 "Goodbye, word"]]]))})

(defn routes [system]
  [["/goodbye" {:get {:handler (partial #'goodbye-handler system)}}]])

