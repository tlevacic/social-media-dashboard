(ns app.controllers.darkmode
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.entitydb :as edb]
            [keechma.next.toolbox.logging :as l]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]))


(derive :darkmode ::pipelines/controller)


(def pipelines
  {:keechma.on/start (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                                (reset! state* false))
   :change-theme-mode (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                                 (reset! state* value))})

(defmethod ctrl/prep :darkmode [ctrl]
  (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state :darkmode [_ state {:keys [entitydb]}]
  state)