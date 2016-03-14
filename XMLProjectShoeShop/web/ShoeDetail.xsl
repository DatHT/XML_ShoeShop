<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : shoeDetail.xsl
    Created on : February 6, 2016, 3:26 PM
    Author     : Daniel
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:param name="shoeId" select="0" />
    <xsl:template match="shoe">
        <xsl:if test="id=$shoeId">
            <div class="main-content-wrapper">
                <div class="left-column">
                    <div class="left-col-content">
                        <div class="left-col-content-title">
                            <h1 id="title-header">
                                <xsl:value-of select="name"/>
                            </h1>
                        </div>
                        <div class="row">
                            <div style="margin-right:10px;line-height:17px;margin-top:5px;">
                                <b>Mã Sản phẩm: </b> 
                                <xsl:value-of select="shoeCode"/>
                            </div>
                            <div style="margin-right:10px;line-height:17px;margin-top:5px;">
                                <b>Giá: </b>
                                <span class="giaban"> 
                                     <xsl:value-of select='format-number(price, "###,###")'/> vnd</span>
                            </div>
                            <div style="margin-right:10px;line-height:17px;margin-top:5px;">
                                <b>Bảo hành: </b> 
                                <xsl:value-of select="guarantee"/>
                            </div>
                            
                            <div style="margin-right:10px;line-height:17px;margin-top:5px;">
                                <b>Tình trạng: </b> 
                                <xsl:if test="status = 1">
                                    Còn hàng
                                </xsl:if>
                                <xsl:if test="not(status = 1)">
                                    Hết hàng
                                </xsl:if>
                            </div>

                            <div style="margin-top:10px;margin-bottom:10px;text-align:center;">
                                <div style="margin-right:10px;float:left;line-height:17px;">
                                    <b>Chọn Size: </b>
                                </div>
                                <div name="mySize">
                                <div style="width:30px;margin-right:10px;float:left;line-height:17px;margin-bottom:5px;">
                                    <input id="r1" name="size" type="radio" value="39" />39 </div>
                                <div style="width:30px;margin-right:10px;float:left;line-height:17px;margin-bottom:5px;">
                                    <input id="r2" name="size" type="radio" value="40"/>40 </div>
                                <div style="width:30px;margin-right:10px;float:left;line-height:17px;margin-bottom:5px;">
                                    <input id="r3" name="size" type="radio" value="41"/>41 </div>
                                <div style="width:30px;margin-right:10px;float:left;line-height:17px;margin-bottom:5px;">
                                    <input id="r4" name="size" type="radio" value="42"/>42 </div>
                                <div style="width:30px;margin-right:10px;float:left;line-height:17px;margin-bottom:5px;">
                                    <input id="r5" name="size" type="radio" value="43"/>43 </div>
                                <div style="clear:both;"></div>
                                </div>
                            </div>
                            <button class="btn" onclick="addToCart({id},'{name}','{thumbPath}',{price})">Thêm vào giỏ</button>
                            <br/>
                                <div style="margin-top:10px;text-align:justify;margin-right:10px;"></div>

                        </div>
                    </div>
                    <h1 style="color:#5fa1c7">Thông tin sản phẩm</h1>
                    <br/> 
                    <br/>
                    <xsl:for-each select="images/item">
                        <p style="text-align: center;">
                            <xsl:element name="img">
                                <xsl:attribute name="src">
                                    <xsl:text>/static/</xsl:text><xsl:value-of select="." />
                                </xsl:attribute>
                                <xsl:attribute name="width">
                                    <xsl:text>600</xsl:text>
                                </xsl:attribute>
                                <xsl:attribute name="alt">
                                    <xsl:text>themedemic</xsl:text>
                                </xsl:attribute>
                                <xsl:attribute name="height">
                                    <xsl:text>600</xsl:text>
                                </xsl:attribute>
                                <xsl:attribute name="style">
                                    <xsl:text>height: auto;</xsl:text>
                                </xsl:attribute>
                            </xsl:element>
                    </p>
                    </xsl:for-each>
                </div>
                <div class="right-column">
                    <div class="right-col-content">
                        <div class="right-col-content-title ">
                            <h1>Detail</h1>
                        </div>
                        <xsl:element name="img">
                                <xsl:attribute name="src">
                                    <xsl:text>/static/</xsl:text><xsl:value-of select="thumbPath" />
                                </xsl:attribute>
                                <xsl:attribute name="class">
                                    <xsl:text>image-border</xsl:text>
                                </xsl:attribute>
                                <xsl:attribute name="alt">
                                    <xsl:text>themedemic</xsl:text>
                                </xsl:attribute>
                            </xsl:element>
                    </div>
                </div> 
        </div>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>
