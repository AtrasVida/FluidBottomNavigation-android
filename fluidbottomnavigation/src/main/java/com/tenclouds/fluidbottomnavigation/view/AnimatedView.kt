package com.tenclouds.fluidbottomnavigation.view

import android.animation.Animator
import android.content.Context
import com.tenclouds.fluidbottomnavigation.R

internal interface AnimatedView {

    fun selectAnimator(): Animator
    fun deselectAnimator(): Animator

    fun getItemTransitionYValue(context: Context,isInsideMode:Boolean) =
            -(context.resources?.getDimension(if(!isInsideMode) R.dimen.fluidBottomNavigationItemTranslationY else R.dimen.fluidBottomNavigationItemTranslationYInsideMode) ?: 0f)

    fun getItemOvershootTransitionYValue(context: Context,isInsideMode:Boolean) =
            getItemTransitionYValue(context,isInsideMode) * 11 / 10
}