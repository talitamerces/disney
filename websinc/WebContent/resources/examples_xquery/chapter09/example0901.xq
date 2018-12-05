(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-1. Useful function: add-attribute :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:add-attribute
 ($element as element(), $name as xs:string,
  $value as xs:anyAtomicType?) as element() {
   element { node-name($element)}
           { attribute {$name} {$value},
             $element/@*,
             $element/node() }
};
