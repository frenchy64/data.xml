(ns clojure.data.xml.process
  {:lang :core.typed
   :core.typed {:features #{:runtime-infer}}}
  (:require [clojure.data.xml.event :refer [element-nss] :as evt]
            [clojure.data.xml.name :as name :refer [gen-prefix *gen-prefix-counter* qname-uri]]
            [clojure.data.xml.node :refer [element] :as node]
            [clojure.data.xml.tree :refer [flatten-elements] :as tree]
            [clojure.core.typed :as t]
            [clojure.spec :as s]
            [clojure.string :as str]))

;; Start: Generated by clojure.core.typed - DO NOT EDIT
(declare)
(t/ann clojure.data.xml.name/gen-prefix [:-> t/Str])
(t/ann clojure.data.xml.name/qname-uri clojure.lang.Var)
(t/ann
  aggregate-xmlns
  [clojure.data.xml.node.Element :-> clojure.data.xml.node.Element])
(t/ann find-xmlns [clojure.data.xml.node.Element :-> (t/Set t/Str)])
(t/ann
  qname-uri-xf
  [[clojure.lang.PersistentHashSet$TransientHashSet
    t/Str
    :->
    clojure.lang.PersistentHashSet$TransientHashSet]
   :->
   [clojure.lang.PersistentHashSet$TransientHashSet
    t/Any
    :->
    clojure.lang.PersistentHashSet$TransientHashSet]])
(t/ann
  reduce-tree
  [[clojure.lang.PersistentHashSet$TransientHashSet
    t/Any
    :->
    clojure.lang.PersistentHashSet$TransientHashSet]
   clojure.lang.PersistentHashSet$TransientHashSet
   clojure.data.xml.node.Element
   :->
   clojure.lang.PersistentHashSet$TransientHashSet])
;; End: Generated by clojure.core.typed - DO NOT EDIT
(defn- reduce-tree
  "Optimized reducer for in-order traversal of nodes, with reduce-like accumulator"
  [f init xml]
  (loop [^{::t/ann clojure.lang.PersistentHashSet$TransientHashSet} result init
         ^{::t/ann t/Any} {:as tree [child & next-children :as children] :content} xml
         ^{::t/ann (t/U nil (t/Coll t/Any))} [parent & next-parents :as parents] ()]
    (if (seq children)
      (recur (f result tree)
             child
             (concat next-children parents))
      (if (seq parents)
        (recur (f result tree)
               parent
               next-parents)
        (f result tree)))))

(defn- qname-uri-xf [xf]
  (t/ann-form
    (fn [s el]
      (if (map? el)
        (reduce-kv
         (t/ann-form
           (fn [s attr _] (xf s (qname-uri attr)))
           [clojure.lang.PersistentHashSet$TransientHashSet (t/U ':xmlns javax.xml.namespace.QName) t/Str :-> clojure.lang.PersistentHashSet$TransientHashSet])
         (xf s (qname-uri (:tag el))) (:attrs el))
        s))
    [clojure.lang.PersistentHashSet$TransientHashSet t/Any :-> clojure.lang.PersistentHashSet$TransientHashSet])
  )

(defn find-xmlns
  "Find all xmlns occuring in a root"
  [xml]
  (persistent!
   (reduce-tree (qname-uri-xf conj!)
                (transient #{}) xml)))

(defn aggregate-xmlns
  "Put all occurring xmlns into the root"
  [xml]
  (with-meta
    xml {:clojure.data.xml/nss
         (binding [*gen-prefix-counter* 0]
           (persistent!
            (reduce (t/ann-form
                      (fn [tm uri]
                        (if (str/blank? uri)
                          tm
                          (assoc! tm (keyword "xmlns" (gen-prefix)) uri)))
                      [clojure.lang.PersistentArrayMap$TransientArrayMap t/Str :-> clojure.lang.PersistentArrayMap$TransientArrayMap])
                    (transient {}) (find-xmlns xml))))}))