(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 14-3. Alternative to a typeswitch expression :)

if ($myProduct instance of element(*,prod:HatType))
then xs:string($myProduct/size)
else if ($myProduct instance of element(*,prod:ShirtType))
     then xs:string(concat($myProduct/size/@system, ": ", $myProduct/size))
     else if ($myProduct instance of element(*,prod:UmbrellaType))
          then "none"
          else "n/a"
