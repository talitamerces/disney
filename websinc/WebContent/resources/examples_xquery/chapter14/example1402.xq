(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 14-2. Binding variables to typeswitch expressions :)

typeswitch ($myProduct)
  case $h as element(*,prod:HatType) return xs:string($h/size)
  case $s as element(*,prod:ShirtType)
       return xs:string(concat($s/size/@system, ": ", $s/size))
  case element(*,prod:UmbrellaType) return "none"
  default return "n/a"

