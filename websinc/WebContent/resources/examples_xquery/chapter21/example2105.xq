(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 21-5. Function that displays processing instructions :)

declare function local:displayPIValue
  ($pi as processing-instruction())as xs:string {

  concat("Target is ", name($pi),
         " and content is ", string($pi))
};
