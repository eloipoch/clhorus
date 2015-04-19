(ns clhorus.context.analytics.core
  (:require [com.stuartsierra.component :as component]
            [clhorus.context.analytics.infrastructure.persistence.korma.connection]
            [clhorus.context.analytics.module.user.user-module-component :refer [user-module-system]])
  (:import (clhorus.context.analytics.infrastructure.persistence.korma.connection DatabaseKormaComponent)))

(defn context-analytics-system [config-options]
  (component/system-map
    :database-analytics (DatabaseKormaComponent. (:database config-options))
    :user-module (component/using (user-module-system) [:database-analytics
                                                        :domain-event-publisher])
    ))
