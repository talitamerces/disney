(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 8-4. Passing the context item to the function :)
declare function local:prod2ndDigit($prod as element()?) as xs:string? {
    substring($prod/number, 2, 1)
};
doc("catalog.xml")//product[local:prod2ndDigit(.) > '5']

