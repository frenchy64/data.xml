(ns clojure.data.xml.test-with-specs-xml
  (:require [clojure.data.xml :as xml]
            [clojure.data.xml.infer-xml :as infer]
						[clojure.spec.test :as stest]))

(defn activate-specs []
	(stest/instrument 
		(filter (comp #{'clojure.data.xml} symbol namespace)
						(stest/instrumentable-syms))))

(println "Activated specs:\n" (activate-specs))
(println "To prove specs are actually enabled, here is a bad call to (declare-ns nil)")
(println
  (try (xml/declare-ns nil)
       (catch Throwable e e)))

(prn "The above lines should show a spec error from a bad call to (declare-ns nil)")

(infer/exercise-tests)
