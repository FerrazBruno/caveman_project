(ns caveman.main
  (:require [caveman.system :as system]))

(defn -main []
  (system/start-system))
