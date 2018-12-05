(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 10-10. Namespace declaration impact on input elements :)

<report xmlns="http://datypic.com/report">
  <firstChild/>
  {doc("prod_ns.xml")/*}
</report>
