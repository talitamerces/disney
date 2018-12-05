(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 15-4. Avoid unnecessary sorting :)

(: More efficient query :)

unordered {
  let $doc := doc("catalog.xml")
  return $doc//(number|name)
}
