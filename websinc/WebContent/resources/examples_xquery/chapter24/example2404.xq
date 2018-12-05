(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 24-4. Grouping in XQuery :)

for $d in distinct-values(doc("catalog2.xml")//product/@dept)
return 
  <result dept="{$d}" 
          count="{count(doc("catalog2.xml")//product[@dept = $d])}"/>



