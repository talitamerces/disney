(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: change-element-ns-deep :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:change-element-ns-deep
($element as element(), $newns as xs:string) as element() {
  let $newName := QName ($newns, local-name($element))
  return (element {$newName}
   {$element/@*,
    for $child in $element/node()
    return if ($child instance of element())
           then functx:change-element-ns-deep($child, $newns)
           else $child
    }
  )
};

(: Example call :)
<test xmlns:pre="pre">{
  functx:change-element-ns-deep(
    <pre:x><pre:y>123</pre:y></pre:x>, "http://new")
}</test>






