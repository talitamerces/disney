(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-17. Reducing complexity :)
let $tempResults:= for $item in doc("order.xml")//item,
                       $product in doc("catalog.xml")//product
                   where $item/@num = $product/number
                   return <item num="{$item/@num}" name="{$product/name}"
                              color="{$item/@color}"
                              quant="{$item/@quantity}"/>
return <table>
        <tr>
          <th>#</th><th>Name</th><th>Color</th><th>Quan</th>
        </tr>
        {for $lineItem in $tempResults
         return <tr>
                  <td>{data($lineItem/@num)}</td>
                  <td>{data($lineItem/@name)}</td>
                  <td>{data($lineItem/@color)}</td>
                  <td>{data($lineItem/@quant)}</td>
                </tr>
        }
     </table>
