package com.example.login_and_signup

abstract class SwipeControllerActions {
    open fun onRightClicked(position: Int) {}
    abstract fun onLeftClicked(position: Int)
}