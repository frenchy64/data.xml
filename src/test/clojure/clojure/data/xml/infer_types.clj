(ns clojure.data.xml.infer-types
  (:require
    [clojure.test :refer :all]
    [clojure.core.typed :as t]
    [clojure.core.typed.runtime-infer :as infer]))

(defn delete-anns [nss]
  (doseq [ns nss]
    (infer/delete-generated-annotations
      ns
      {:ns 'clojure.data.xml})))

(defn infer-anns [nss]
  (doseq [ns nss]
    (t/runtime-infer :ns ns)))

(def infer-files
  '[clojure.data.xml
    clojure.data.xml.process
    clojure.data.xml.impl
    clojure.data.xml.event
    ])

;; FIXME shouldn't need this, but some types
;; don't compile
(delete-anns infer-files)

(def tests 
  '[clojure.data.xml.test-entities
    clojure.data.xml.test-sexp
    clojure.data.xml.test-emit
    clojure.data.xml.test-process
    clojure.data.xml.test-names
    clojure.data.xml.test-pprint
    clojure.data.xml.test-seq-tree
    clojure.data.xml.test-parse
    ])

(apply require tests)
(apply run-tests tests)

(infer-anns infer-files)
