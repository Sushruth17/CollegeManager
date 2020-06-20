package com.example.login_and_signup

abstract class SwipeControllerActions {
    fun onLeftClicked(position: Int) {}
    open fun onRightClicked(position: Int) {}
}