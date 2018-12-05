(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-9. Using a namespace declaration in a constructor :)
<xhtml:html xmlns:xhtml="http://www.w3.org/1999/xhtml">
  <xhtml:h1 class="itemHdr">Product Catalog</xhtml:h1>
  <xhtml:ul>{
    for $prod in doc("catalog.xml")/catalog/product
    return <xhtml:li class="{$prod/@dept}">number: {
                               data($prod/number)}</xhtml:li>
  }</xhtml:ul>
</xhtml:html>






