package com.tenclouds.fluidbottomnavigation

import android.animation.AnimatorSet
import android.view.View
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.tenclouds.fluidbottomnavigation.extension.translationYAnimator
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.item.view.circle
import kotlinx.android.synthetic.main.item.view.icon
import kotlinx.android.synthetic.main.item.view.title
import kotlinx.android.synthetic.main.item_inside.view.*


internal fun View.animateSelectItemView(isInsideMode: Boolean) =
        AnimatorSet()
                .apply {
                    if (isInsideMode) playTogether(
                            circle.selectAnimator(),
                            icon.selectAnimator(),
                            iconContainer.selectAnimator()

                    ) else
                        playTogether(
                                circle.selectAnimator(),
                                icon.selectAnimator(),
                                title.selectAnimator(),
                                rectangle.selectAnimator(),
                                topContainer.selectAnimator())
                }
                .start()

internal fun View.animateDeselectItemView(isInsideMode: Boolean) =
        AnimatorSet()
                .apply {
                    if (isInsideMode) playTogether(
                            circle.deselectAnimator(),
                            icon.deselectAnimator(),
                            iconContainer.deselectAnimator()
                    )
                    else playTogether(
                            circle.deselectAnimator(),
                            icon.deselectAnimator(),
                            title.deselectAnimator(),
                            rectangle.deselectAnimator(),
                            topContainer.deselectAnimator())
                }
                .start()

internal fun View.animateShow() =
        AnimatorSet()
                .apply {
                    play(translationYAnimator(
                            height.toFloat(),
                            0f,
                            3 * KEY_FRAME_IN_MS,
                            LinearOutSlowInInterpolator()))
                }
                .start()

internal fun View.animateHide() =
        AnimatorSet()
                .apply {
                    play(translationYAnimator(
                            0f,
                            height.toFloat(),
                            3 * KEY_FRAME_IN_MS,
                            LinearOutSlowInInterpolator()))
                }
                .start()