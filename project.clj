(defproject org.clojure/data.xml "0-DEVELOPMENT"
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/test/resources"]
  :injections [(require 'clojure.core.typed)
               (clojure.core.typed/install
                 #{:load})]
  :monkeypatch-clojure-test false
  :repl-options {:timeout 3000000}
  :dependencies [[org.clojure/clojure "1.9.0-alpha15"]
                 [org.clojure/core.typed "0.3.33-SNAPSHOT"]])
