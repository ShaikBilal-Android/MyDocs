package com.example.studenttask.Models

import android.media.Image

class StudentModel {
    var img:String?=null
    var nam:String?=null
    var phonenumb:String?=null
    var rollnumb:Int?=null
    constructor()
    constructor(img: String?, nam: String?, phonenumb: String?, rollnumb: Int?) {
        this.img = img
        this.nam = nam
        this.phonenumb = phonenumb
        this.rollnumb = rollnumb
    }

    constructor(nam: String?, phonenumb: String?, rollnumb: Int?) {
        this.nam = nam
        this.phonenumb = phonenumb
        this.rollnumb = rollnumb
    }


}