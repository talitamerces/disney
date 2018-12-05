(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 8-3. Invalid use of context in a function body :)
declare function local:prod2ndDigit() as xs:string? {
    substring(number, 2, 1)
};
doc("catalog.xml")//product[local:prod2ndDigit() > '5']
