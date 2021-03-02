package com.tenclouds.fluidbottomnavigationexample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tenclouds.fluidbottomnavigation.FluidBottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fluidBottomNavigation.isInsideMode = true
        fluidBottomNavigation.accentColor = ContextCompat.getColor(this, R.color.colorWhite)
        //fluidBottomNavigation.backRes = R.drawable.fluidbottomnavigationexample_shadow32dpi3
        fluidBottomNavigation.itemBackRes = ContextCompat.getColor(this, R.color.colorFullAlpha)//R.drawable.bottom_navigation_bg
        fluidBottomNavigation.selectorColor = ContextCompat.getColor(this, R.color.colorFullAlpha)
        //fluidBottomNavigation.selectorBg = R.drawable.fluidbottomnavigationexample_shadow32dpi3
        fluidBottomNavigation.selectorBg = R.drawable.shadow4dpi
        fluidBottomNavigation.textColor = ContextCompat.getColor(this, R.color.colorLightNavy)
        fluidBottomNavigation.iconColor = ContextCompat.getColor(this, R.color.colorWarmGreyTwo)
        fluidBottomNavigation.iconSelectedColor =
                ContextCompat.getColor(this, R.color.colorLightNavy)


        fluidBottomNavigation.items =
                listOf(
                        FluidBottomNavigationItem(
                                getString(R.string.news),
                                ContextCompat.getDrawable(this, R.drawable.ic_news),true),
                        FluidBottomNavigationItem(
                                getString(R.string.inbox),
                                ContextCompat.getDrawable(this, R.drawable.ic_inbox),true),
                        FluidBottomNavigationItem(
                                getString(R.string.calendar),
                                ContextCompat.getDrawable(this, R.drawable.ic_calendar),false),
                        FluidBottomNavigationItem(
                                getString(R.string.chat),
                                ContextCompat.getDrawable(this, R.drawable.ic_chat),true),
                        FluidBottomNavigationItem(
                                getString(R.string.profile),
                                ContextCompat.getDrawable(this, R.drawable.ic_profile),true))

        Handler().postDelayed({ fluidBottomNavigation.setItemNotification(3,5)},5000)

    }

}