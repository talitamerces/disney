(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 21-6. Processing instruction constructors :)

<ul>{
  <?doc-processor version="4.3"?>,
  processing-instruction doc-processor2 {'version="4.3"'},
  processing-instruction {concat("doc-processor", "3")}
        {concat('version="', '4.3', '"')}
}</ul>
