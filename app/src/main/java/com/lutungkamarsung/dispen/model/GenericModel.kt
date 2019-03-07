package com.lutungkamarsung.dispen.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GenericModel {
    @SerializedName("result")
    @Expose
    var result: Boolean? = null

    @SerializedName("value")
    @Expose
    var value: String? = ""

    constructor(value: String? = "") {
        this.value = value
    }
}