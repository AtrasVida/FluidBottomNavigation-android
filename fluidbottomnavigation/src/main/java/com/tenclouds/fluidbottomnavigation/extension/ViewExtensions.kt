package com.tenclouds.fluidbottomnavigation.extension

import android.annotation.TargetApi
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.support.v4.widget.ImageViewCompat
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.ImageView
import com.tenclouds.fluidbottomnavigation.FluidBottomNavigation

internal fun View.visible() {
    this.visibility = View.VISIBLE
}

internal fun View.invisible() {
    this.visibility = View.INVISIBLE
}

internal fun View.gone() {
    this.visibility = View.GONE
}

internal fun View?.getColor(colorResource: Int?) =
        colorResource ?: 0

internal fun ImageView.setTintColor(color: Int) =
        ImageViewCompat.setImageTintList(
                this,
                ColorStateList.valueOf(color))

@Suppress("DEPRECATION")
fun ViewTreeObserver.removeOnGlobalLayoutListenerCompat(listener: ViewTreeObserver.OnGlobalLayoutListener) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        this.removeOnGlobalLayoutListener(listener)
    } else {
        this.removeGlobalOnLayoutListener(listener)
    }
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
internal fun FluidBottomNavigation.calculateHeight(layoutHeight: Int): Int {
    var navigationLayoutHeight = layoutHeight
    var navigationBarHeight = 0

    resources.getIdentifier(
            "navigation_bar_height",
            "dimen",
            "android"
    )
            .let {
                if (it > 0)
                    navigationBarHeight = resources.getDimensionPixelSize(it)
            }

    intArrayOf(android.R.attr.windowTranslucentNavigation)
            .let {
                with(context.theme
                        .obtainStyledAttributes(it)) {
                    val translucentNavigation = getBoolean(0, true)
                    if (hasImmersive(context) && !translucentNavigation) {
                        navigationLayoutHeight += navigationBarHeight
                    }
                    recycle()
                }
            }

    return navigationLayoutHeight
}

private fun hasImmersive(context: Context) =
        with(
                (context
                        .getSystemService(Context.WINDOW_SERVICE) as WindowManager)
                        .defaultDisplay) {
            Pair(getMetrics(), getRealMetrics())
        }
                .let { (displayMetrics, realDisplayMetrics) ->
                    realDisplayMetrics.widthPixels > displayMetrics.widthPixels
                            || realDisplayMetrics.heightPixels > displayMetrics.heightPixels
                }

private fun Display.getMetrics() =
        DisplayMetrics().also { this.getMetrics(it) }

private fun Display.getRealMetrics() =
        DisplayMetrics()
                .let {
                    when {
                        Build.VERSION.SDK_INT >= 17 -> it.also { this.getRealMetrics(it) }
                        Build.VERSION.SDK_INT >= 15 ->
                            try {
                                val getRawHeight = Display::class.java.getMethod("getRawHeight")
                                val getRawWidth = Display::class.java.getMethod("getRawWidth")
                                DisplayMetrics()
                                        .apply {
                                            widthPixels = getRawWidth.invoke(this) as Int
                                            heightPixels = getRawHeight.invoke(this) as Int
                                        }
                            } catch (e: Exception) {
                                DisplayMetrics().apply {
                                    @Suppress("DEPRECATION")
                                    widthPixels = this@getRealMetrics.width
                                    @Suppress("DEPRECATION")
                                    heightPixels = this@getRealMetrics.height
                                }
                            }
                        else -> DisplayMetrics().apply {
                            @Suppress("DEPRECATION")
                            widthPixels = this@getRealMetrics.width
                            @Suppress("DEPRECATION")
                            heightPixels = this@getRealMetrics.height
                        }
                    }
                }