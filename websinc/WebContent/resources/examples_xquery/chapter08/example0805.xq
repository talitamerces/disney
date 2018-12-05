(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 8-5. A recursive function :)
declare namespace functx = "http://www.functx.com";
declare function functx:num-descendant-elements
  ($el as element()) as xs:integer {
    sum(for $child in $el/*
        return functx:num-descendant-elements($child) + 1)
};
