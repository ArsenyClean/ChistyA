<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<entries>\n
		<xsl:apply-templates select="/entries/entry"/>
		</entries>
	</xsl:template>
	<xsl:template match="entry">
		<xsl:element name="entry">
			<xsl:attribute name="field">
		<xsl:value-of select="field"/>
			</xsl:attribute>
		</xsl:element>\n
	</xsl:template>
</xsl:stylesheet>
