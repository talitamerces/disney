(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 14-6. A FLWOR with a type declaration :)

for $prod as element(*,ProductType) in doc("catalog.xml")/catalog/*
order by $prod/name
return $prod/name

