(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-9. Quantified expression using the every keyword :)
every $dept in doc("catalog.xml")//product/@dept
satisfies ($dept = "ACC")






