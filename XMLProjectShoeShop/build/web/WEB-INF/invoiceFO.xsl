<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : invoiceFO.xsl
    Created on : February 16, 2016, 8:21 AM
    Author     : Daniel
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="x" page-height="8.5in" page-width="11in"
                                       margin-top="0.5in" margin-bottom="0.5in" margin-left="1in" margin-right="1in">
                    <fo:region-body margin-top="0.8in" />
                    <fo:region-before extent="1in" />
                    <fo:region-after extent=".75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>
	
            <fo:page-sequence master-reference="x">
        
            
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="14pt" font-family="Arial" line-height="24pt" color="cyan" 
                              space-after.optimum="15pt" text-align="left" padding-top="3pt">
                        Thanh Dat Shop
                    </fo:block>
                    <fo:block font-size="14pt" font-family="Arial" line-height="24pt" color="cyan" 
                              space-after.optimum="15pt" text-align="left" padding-top="1pt">
                        Quang Trung, Gò Vấp
                    </fo:block>
                    <fo:block font-size="14pt" font-family="Arial" line-height="24pt" color="cyan" 
                              space-after.optimum="15pt" text-align="left" padding-top="1pt">
                        thanhdatshop@gmail.com
                    </fo:block>
                    <fo:block font-size="14pt" font-family="Arial" line-height="24pt" color="cyan" 
                              space-after.optimum="15pt" text-align="left" padding-top="1pt" >
                        0123456789
                    </fo:block>
                </fo:static-content>
		
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block font-size="18pt" font-family="Arial" line-height="24pt" space-after.optimum="15pt" 
                              text-align="center" padding-top="3pt">
                    </fo:block>
                        
                </fo:static-content>
		
                <fo:flow flow-name="xsl-region-body">
                    <fo:block span="all" font-family="Arial" padding-top="12pt" font-size="2em" font-weight="bold" line-height="2" space-before="1em" space-after="1em" space-before.conditionality="retain" space-after.conditionality="retain" background-color="#EEEEEE">
                        <fo:block start-indent="1em" end-indent="1em" text-align="center">Hóa Đơn</fo:block>
                    </fo:block>
                    <fo:block font-style="italic" font-family="Arial">Khách hàng: <xsl:value-of select="Invoce/cusName" /></fo:block>
                    <fo:block font-style="italic" font-family="Arial">Email: <xsl:value-of select="Invoce/cusEmail" /></fo:block>
                    <fo:block font-style="italic" font-family="Arial">Số điện thoại: <xsl:value-of select="Invoce/cusPhone" /></fo:block>
                    <fo:block font-style="italic" font-family="Arial" padding-bottom="10pt">Địa chỉ: <xsl:value-of select="Invoce/cusAddress" /></fo:block>
                    <fo:block font-family="Arial">
                        <fo:table border-collapse="separate" table-layout="fixed">
                            <fo:table-column column-width="2cm"/>
                            <fo:table-column column-width="10cm"/>
                            <fo:table-column column-width="2cm"/>
                            <fo:table-column column-width="2cm"/>
                            <fo:table-column column-width="5cm"/>
					
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell border-color="blue" border-width="1pt" border-style="solid">
                                        <fo:block font-weight="bold" text-align="center">Stt.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue" border-width="1pt" border-style="solid">
                                        <fo:block font-weight="bold" text-align="center">Sản phẩm</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue" border-width="1pt" border-style="solid">
                                        <fo:block font-weight="bold" text-align="center">Size</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue" border-width="1pt" border-style="solid">
                                        <fo:block font-weight="bold" text-align="center">Số lượng</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue" border-width="1pt" border-style="solid">
                                        <fo:block font-weight="bold" text-align="center">Giá</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each select="Invoce/orderDetail">
                                    
                                    <fo:table-row>
                                        <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:number level="single" count="orderDetail" />.
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="shoeName" />
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="size" />
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="quantity" />
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select='format-number(price, "###,###")' /> vnd
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                        
                        <fo:block font-weight="bold" font-family="Arial" text-align="right" padding-bottom="10pt" padding-top="10pt" padding-right="30pt">Tổng giá: <xsl:value-of select='format-number(Invoce/total, "###,###")' /> vnd</fo:block>
                        <fo:block font-family="Arial" text-align="right">HCM, <xsl:value-of select="Invoce/time" /></fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>
