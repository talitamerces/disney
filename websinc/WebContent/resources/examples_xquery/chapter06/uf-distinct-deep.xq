(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: distinct-deep :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:distinct-deep ($nodes as node()*) as node()*
 {
   for $x in (1 to count($nodes))
   let $node := $nodes[$x]
   let $restOfNodes := subsequence($nodes, $x + 1)
   return if (some $otherNode in $restOfNodes satisfies
              (deep-equal($otherNode, $node)))
          then ()
          else $node
 };

(: Example call :)
functx:distinct-deep(doc("catalog.xml")//product)






