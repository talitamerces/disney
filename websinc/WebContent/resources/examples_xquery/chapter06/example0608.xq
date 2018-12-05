(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-8. Quantified expression using the some keyword :)
some $dept in doc("catalog.xml")//product/@dept
satisfies ($dept = "ACC")





