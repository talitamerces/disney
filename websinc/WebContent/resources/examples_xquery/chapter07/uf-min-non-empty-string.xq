(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: min-non-empty-string :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:min-non-empty-string
($stringSeq as xs:string*) as xs:string? {
   min($stringSeq[. != ''])
};

(: Example call :)
functx:min-non-empty-string(doc("order.xml")//item/@dept)






