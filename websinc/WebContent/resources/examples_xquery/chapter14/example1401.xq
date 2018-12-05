(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 14-1. Binding variables to typeswitch expressions :)

typeswitch ($myProduct)
  case element(*,prod:HatType) return xs:string($myProduct/size)
  case element(*,prod:ShirtType)
       return xs:string(concat($myProduct/size/@system, ": ",
                               $myProduct/size))
  case element(*,prod:UmbrellaType) return "none"
  default return "n/a"
