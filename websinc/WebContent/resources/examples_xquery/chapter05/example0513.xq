(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-13. Simple computed constructor :)
element html {
  element h1 { "Product Catalog" },
  element ul {
    for $prod in doc("catalog.xml")/catalog/product
    return element li {"number:",data($prod/number),", name:",data($prod/name)}
  }
}







