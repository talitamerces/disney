(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 21-9. Function that displays text nodes :)

declare function local:displayTextNodeContent
  ($textNode as text()) as xs:string {
  concat("Content of the text node is ", $textNode)
};
