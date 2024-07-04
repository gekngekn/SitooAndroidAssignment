package com.gekn.sitooandroidassignment.network.models

import com.google.gson.annotations.SerializedName

data class NetworkProductDetails(
    @SerializedName("productid")
    var productid: Int,
    @SerializedName("sku")
    var sku: String,
    @SerializedName("skumanufacturer")
    var skumanufacturer: String,
    @SerializedName("descriptionshort")
    var descriptionshort: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("descriptionhtml")
    var descriptionhtml: String,
    @SerializedName("deliverystatus")
    var deliverystatus: String,
    @SerializedName("moneyprice")
    var moneyprice: String,
    @SerializedName("moneypriceorg")
    var moneypriceorg: String,
    @SerializedName("moneypricein")
    var moneypricein: String,
    @SerializedName("unitlabel")
    var unitlabel: String,
    @SerializedName("allowdecimals")
    var allowdecimals: Boolean,
    @SerializedName("deliveryinfo")
    var deliveryinfo: String,
    @SerializedName("externalinputtitle")
    var externalinputtitle: String,
    @SerializedName("offerisenabled")
    var offerisenabled: Boolean,
    @SerializedName("moneyofferprice")
    var moneyofferprice: String,
    @SerializedName("offertitle")
    var offertitle: String,
    @SerializedName("offerdatestart")
    var offerdatestart: Long?,
    @SerializedName("offerdateend")
    var offerdateend: Long?,
    @SerializedName("active")
    var active: Boolean,
    @SerializedName("activepos")
    var activepos: Boolean,
    @SerializedName("vatid")
    var vatid: Int,
    @SerializedName("deliveryclassid")
    var deliveryclassid: Int,
    @SerializedName("defaultcategoryid")
    var defaultcategoryid: Int?,
    @SerializedName("categories")
    var categories: ArrayList<Int>,
    @SerializedName("manufacturerid")
    var manufacturerid: String?,
    @SerializedName("manufacturerurl")
    var manufacturerurl: String,
    @SerializedName("custom1")
    var custom1: String,
    @SerializedName("custom2")
    var custom2: String,
    @SerializedName("custom3")
    var custom3: String,
    @SerializedName("custom4")
    var custom4: String,
    @SerializedName("custom5")
    var custom5: String,
    @SerializedName("stockcountenable")
    var stockcountenable: Boolean,
    @SerializedName("stockallowbackorder")
    var stockallowbackorder: Boolean,
    @SerializedName("variantparentid")
    var variantparentid: Int?,
    @SerializedName("barcode")
    var barcode: String?,
    @SerializedName("barcodealiases")
    var barcodealiases: ArrayList<String>,
    @SerializedName("similar")
    var similar: ArrayList<Int>,
    @SerializedName("related")
    var related: ArrayList<Int>,
    @SerializedName("accessories")
    var accessories: ArrayList<Int>,
    @SerializedName("offerisactive")
    var offerisactive: Boolean,
    @SerializedName("moneyfinalprice")
    var moneyfinalprice: String,
    @SerializedName("vatvalue")
    var vatvalue: Int?,
    @SerializedName("productgrouptype")
    var productgrouptype: Int?,
    @SerializedName("pricelisthasvolume")
    var pricelisthasvolume: Boolean,
    @SerializedName("variant")
    var variant: ArrayList<String>?,
    @SerializedName("customattributes")
    var customattributes: String?,
    @SerializedName("friendly")
    var friendly: String,
    @SerializedName("seo_title")
    var seoTitle: String,
    @SerializedName("seo_keywords")
    var seoKeywords: String,
    @SerializedName("seo_description")
    var seoDescription: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("datecreated")
    var datecreated: Long,
    @SerializedName("datemodified")
    var datemodified: Long
)