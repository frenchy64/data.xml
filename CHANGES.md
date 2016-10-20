From 0.1.0-beta1 to 0.1.0-beta2
- Add support for emitting DOCTYPEs (DXML-10)
- Fix issue emitting sibling namespaces (DXML-33)
- Fix issue printing defaulted namespaces (DXML-30)

From 0.0.8 to 0.1.0-beta1
- Add support for XML namespaces (DXML-4)
- Fix pull-seq so it produces character events that work with emit-events (DXML-28)
- Removed docs and references to JDK 1.5, data.xml now requires 1.6+
- data.xml now requires Clojure 1.4.0+

From 0.0.7 to 0.0.8
- Remove relection warnings in emit-cdata (DXML-16)
- Added an EPL license file (DXML-19)
- Fixed bug in the handling of CData end tags (DXML-17)
- Added support for emitting booleans and numbers (DXML-14)

From 0.0.6 to 0.0.7
- Fixed bug with args to the indentation function (DXML-7)
- Strings now supported as tag names, previously was only kewords (DXML-8)
- Add CDATA and comments support to sexp-as-element (DXML-11)
- data.xml now properly handles CDATA records that contain an embedded ]]>
  by breaking it into two CDATA sections (DXML-12)