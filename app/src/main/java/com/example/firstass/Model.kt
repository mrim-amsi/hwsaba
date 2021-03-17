package com.example.firstass

import androidx.annotation.Keep

@Keep
 data class Model  (  val id: String,  val  name: String, val  phone: String, val email: String) {
     constructor() : this("" , "", "", "")

 }