(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: open-ref-document :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:open-ref-document
 ($refNode as node()) as document-node()
 {
   let $absoluteURI := resolve-uri($refNode, base-uri($refNode))
   return doc($absoluteURI)
 };

(: Example call :)
let $ref := doc("http://datypic.com/cats.xml")/catalogs/catalog[1]/product[1]/@href
return functx:open-ref-document($ref)







