(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-10. Combining the not function with a quantified expression :)
not(some $dept in doc("catalog.xml")//product/@dept
    satisfies ($dept = "ACC"))






