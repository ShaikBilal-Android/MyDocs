package com.example.firebasertdb

data class PersonData(var name:String, var age:Int, var mail:String, var pass:String){
    constructor(): this("",0,"","")
}
