(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 14-4. A query without a treat expression :)

if ($myProduct instance of element(*,prod:HatType))
then <p>The size is: {data($myProduct/size)}</p>
else ()
