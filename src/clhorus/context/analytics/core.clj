(ns clhorus.context.analytics.core
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.analytics.config :refer [analytics-config]]
            [clhorus.context.analytics.infrastructure.persistence.korma-component]
            [clhorus.context.analytics.module.user.user-module-component :refer [user-module-system]])
  (:import (clhorus.context.analytics.infrastructure.persistence.korma_component DatabaseKormaComponent)))

(defn context-analytics-system []
  (component/system-map
    :database-analytics (DatabaseKormaComponent. (:database analytics-config))
    :user-module (component/using (user-module-system) [:database-analytics
                                                        :domain-event-publisher])
    ))
