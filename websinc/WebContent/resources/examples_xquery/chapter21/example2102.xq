(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 21-2. Function that processes comments :)

declare function local:createCommentElement
  ($commentToAdd as comment()) as element() {
  <comment>{string($commentToAdd)}</comment>
};
