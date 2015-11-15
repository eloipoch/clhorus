(ns clhorus.context.analytics.module.analytics-context.infrastructure.persistence.analytics-context-repository-mysql
  (:require [clhorus.context.analytics.module.analytics-context.domain.analytics-context-repository :refer [AnalyticsContextRepository]]))

(defrecord AnalyticsContextRepositoryMysql []
  AnalyticsContextRepository
  (add [_ analytics-context] nil))

(defn new-analytics-context-repository-mysql []
  (map->AnalyticsContextRepositoryMysql {}))
