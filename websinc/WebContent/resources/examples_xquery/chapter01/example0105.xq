(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 1-5. Simple FLWOR :)
for $prod in doc("catalog.xml")/catalog/product
where $prod/@dept = "ACC"
order by $prod/name
return $prod/name




