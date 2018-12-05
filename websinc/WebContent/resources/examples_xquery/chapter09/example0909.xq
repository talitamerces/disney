(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-9. Attempting to use the position function :)
for $prod in doc("catalog.xml")//product[@dept = ("ACC", "WMN")]
return <p>{$prod/position()}. {data($prod/name)}</p>
