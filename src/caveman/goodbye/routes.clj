(ns caveman.goodbye.routes
  (:require [caveman.page-html.core :as page-html]
            [hiccup2.core :as hiccup]))

(defn goodbye-handler [_system _requres]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str (hiccup/html
                [:html
                 [:body
                  (page-html/view
                    :body [:h1 "Goodbye, word"])]]))})

(defn routes [system]
  [["/goodbye" {:get {:handler (partial #'goodbye-handler system)}}]])

