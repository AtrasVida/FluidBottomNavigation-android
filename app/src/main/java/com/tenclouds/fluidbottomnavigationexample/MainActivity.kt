package com.tenclouds.fluidbottomnavigationexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tenclouds.fluidbottomnavigation.FluidBottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fluidBottomNavigation.accentColor = ContextCompat.getColor(this, R.color.colorWhite)
        //fluidBottomNavigation.backRes = R.drawable.fluidbottomnavigationexample_shadow32dpi3
        fluidBottomNavigation.itemBackRes = ContextCompat.getColor(this, R.color.colorFullAlpha)//R.drawable.bottom_navigation_bg
        fluidBottomNavigation.selectorColor = ContextCompat.getColor(this, R.color.colorFullAlpha)
        fluidBottomNavigation.selectorBg = R.drawable.fluidbottomnavigationexample_shadow32dpi3
        fluidBottomNavigation.textColor = ContextCompat.getColor(this, R.color.colorLightNavy)
        fluidBottomNavigation.iconColor = ContextCompat.getColor(this, R.color.colorWarmGreyTwo)
        fluidBottomNavigation.iconSelectedColor =
                ContextCompat.getColor(this, R.color.colorLightNavy)


        fluidBottomNavigation.items =
                listOf(
                        FluidBottomNavigationItem(
                                getString(R.string.news),
                                ContextCompat.getDrawable(this, R.drawable.ic_news)),
                        FluidBottomNavigationItem(
                                getString(R.string.inbox),
                                ContextCompat.getDrawable(this, R.drawable.ic_inbox)),
                        FluidBottomNavigationItem(
                                getString(R.string.calendar),
                                ContextCompat.getDrawable(this, R.drawable.ic_calendar)),
                        FluidBottomNavigationItem(
                                getString(R.string.chat),
                                ContextCompat.getDrawable(this, R.drawable.ic_chat)),
                        FluidBottomNavigationItem(
                                getString(R.string.profile),
                                ContextCompat.getDrawable(this, R.drawable.ic_profile)))
    }
}