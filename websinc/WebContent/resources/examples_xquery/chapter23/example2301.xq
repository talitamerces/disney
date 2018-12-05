(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 23-1. Option declarations :)

declare namespace saxon="http://saxon.sf.net/";

declare option saxon:default "25";
declare variable $maxRowsToReturn external;

declare option saxon:output "method=xhtml";
