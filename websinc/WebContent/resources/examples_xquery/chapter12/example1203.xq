(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 12-3. Module :)

module namespace strings = "http://datypic.com/strings";
declare variable $strings:maxStringLength := 32;
declare function strings:trim($arg as xs:string?) as xs:string? {
  "function body here"
};

