<!-- XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 -->
<!-- Example 25-3. A "push" stylesheet on narrative content -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="p">
    <para>
      <xsl:apply-templates/>
    </para>
  </xsl:template>
  <xsl:template match="b">
    <Strong><xsl:apply-templates/></Strong>
  </xsl:template>
  <xsl:template match="i">
     <Italics><xsl:apply-templates/></Italics>
  </xsl:template>
</xsl:stylesheet>


