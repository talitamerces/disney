(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: max-string :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:max-string ($stringSeq as xs:string*) as xs:string?{
   max($stringSeq)
};

(: Example call :)
functx:max-string(doc("order.xml")//item/@dept)






