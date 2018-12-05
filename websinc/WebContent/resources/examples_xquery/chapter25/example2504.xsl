<!-- XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 -->
<!-- Example 25-4. An attempt at a "pull" stylesheet on narrative content -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="p">
    <para>
        <xsl:for-each select="node()">
          <xsl:choose>
            <xsl:when test="self::text()">
               <xsl:value-of select="."/>
            </xsl:when>
            <xsl:when test="self::b">
              <Strong><xsl:value-of select="."/></Strong>
            </xsl:when>
            <xsl:when test="self::i">
              <Italics><xsl:value-of select="."/></Italics>
            </xsl:when>
          </xsl:choose>
        </xsl:for-each>
    </para>
  </xsl:template>
</xsl:stylesheet>


