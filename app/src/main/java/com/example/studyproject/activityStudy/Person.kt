package com.example.studyproject.activityStudy

import java.io.Serializable


/***
 * 构造
 */
class Person(var firstname:String,var lastname:String,var age:Int,): Serializable {
    constructor(firstname: String,lastname: String) : this(firstname,lastname,18) {
        if (firstname =="wenbo" && lastname == "xue"){
            this.firstname = "linlin"
            this.lastname = "deng"
        }
    }
}