(ns caveman.static.routes
  (:require [ring.util.response :as response]))

(defn favicon-icon-handler [& _]
  (let [r (response/resource-response "/favicon.ico")]
    (clojure.pprint/pprint r)
    r))

(defn routes [_]
  [["/favicon.ico" favicon-icon-handler]])

