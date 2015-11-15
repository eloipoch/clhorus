(ns clhorus.context.analytics.module.analytics-context.analytics_context_module
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.analytics.module.analytics-context.infrastructure.persistence.analytics-context-repository-mysql :refer [new-analytics-context-repository-mysql]]))

(defn analytics-context-module []
  (component/system-map
    :analytics-context-repository (-> (new-analytics-context-repository-mysql) (component/using [:database]))))
