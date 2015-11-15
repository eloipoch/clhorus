(ns clhorus.context.analytics.module.analytics-context.domain.analytics-context-repository)

(defprotocol AnalyticsContextRepository
  (add [this analytics_context] "add an analytics context"))
