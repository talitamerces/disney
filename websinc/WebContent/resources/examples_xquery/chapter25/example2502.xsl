<!-- XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 -->
<!-- Example 25-2. A "push" stylesheet -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="catalog">
    <ul>
      <xsl:apply-templates/>
    </ul>
  </xsl:template>
  <xsl:template match="product">
    <xsl:apply-templates/>
  </xsl:template>
  <xsl:template match="number">
     <li>Product #: <xsl:value-of select="."/></li>
  </xsl:template>
  <xsl:template match="name">
     <li>Product name: <xsl:value-of select="."/></li>
  </xsl:template>
  <xsl:template match="node()"/>
</xsl:stylesheet>


