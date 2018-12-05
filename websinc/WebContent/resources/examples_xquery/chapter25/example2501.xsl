<!-- XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 -->
<!-- Example 25-1. A "pull" stylesheet -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="catalog">
    <ul>
        <xsl:for-each select="product">
          <li>Product #: <xsl:value-of select="number"/></li>
          <li>Product name: <xsl:value-of select="name"/></li>
        </xsl:for-each>
    </ul>
  </xsl:template>
</xsl:stylesheet>

