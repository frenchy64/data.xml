#!/bin/sh

echo 'Disabling :lang on clojure.data.xml\n'
perl -pi -e 's/:lang :core.typed/;:lang :core.typed/g' src/main/clojure/clojure/data/xml.clj
lein test :only clojure.data.xml.test-with-specs-xml
perl -pi -e 's/;:lang :core.typed/:lang :core.typed/g' src/main/clojure/clojure/data/xml.clj
