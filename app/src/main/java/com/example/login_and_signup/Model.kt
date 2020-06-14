package com.example.login_and_signup

public class Model{
    lateinit var id:String
    lateinit var name:String
    lateinit var age:String

    constructor(id: String,name:String,age:String) {
        this.id = id
        this.name = name
        this.age = age
    }

    constructor()
}