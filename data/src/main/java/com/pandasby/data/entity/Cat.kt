package com.pandasby.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cat (
	@SerializedName("breeds") val breeds : List<String>,
	@SerializedName("id") @Expose val id : String,
	@SerializedName("url") @Expose val url : String,
	@SerializedName("width") @Expose val width : Int,
	@SerializedName("height") @Expose val height : Int
)