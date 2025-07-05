(ns caveman.routes
  (:require [caveman.cave.routes :as cave-routes]
            [caveman.goodbye.routes :as goodbye-routes]
            [caveman.hello.routes :as hello-routes]
            [caveman.system :as-alias system]
            [clojure.tools.logging :as log]
            [hiccup2.core :as hiccup]
            [next.jdbc :as jdbc]
            [reitit.ring :as reitit-ring]))

(defn routes [system]
  [""
   (cave-routes/routes system)
   (hello-routes/routes system)
   (goodbye-routes/routes system)])

(defn not-found-handler [_request]
  {:status 404
   :headers {"Content-type" "text/html"}
   :body (str (hiccup/html
               [:html
                [:body
                 [:h1 "Not found"]]]))})

(defn root-handler
  ([system request]
   ((root-handler system) request))
  ([system]
   (let [handler (reitit-ring/ring-handler
                  (reitit-ring/router
                   (routes system))
                  #'not-found-handler)]
     (fn root-handler [request]
       (log/info (str (:request-method request) " - " (:uri request)))
       (handler request)))))

