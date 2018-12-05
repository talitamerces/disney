(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 14-5. A query with a treat expression :)

if ($myProduct instance of element(*,prod:HatType))
then
  <p>The size is: {data(($myProduct treat as element(*,prod:HatType))/size)}</p>
else ()

