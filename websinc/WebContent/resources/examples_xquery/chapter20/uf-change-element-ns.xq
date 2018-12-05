(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: change-element-ns :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:change-element-ns
($element as element(), $newns as xs:string) as element()
 {
   let $newName := QName($newns, local-name($element))
   return (element {$newName} {$element/@*, $element/node()})
 };

(: Example call :)
<test xmlns:pre="pre">{
  functx:change-element-ns(
    <pre:x><pre:y>123</pre:y></pre:x>, "http://new")
}</test>






