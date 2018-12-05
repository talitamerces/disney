(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: get-ID :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:get-ID($element as element()?) as xs:ID?
{ data($element/@*[id(.) is ..])};

(: Example call :)
functx:get-ID(doc("book.xml")//section[1])







