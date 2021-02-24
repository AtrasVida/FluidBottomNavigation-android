package com.tenclouds.fluidbottomnavigation.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.tenclouds.fluidbottomnavigation.KEY_FRAME_IN_MS
import com.tenclouds.fluidbottomnavigation.extension.interpolators
import com.tenclouds.fluidbottomnavigation.extension.scaleAnimator
import com.tenclouds.fluidbottomnavigation.extension.translationYAnimator

internal class IconContainerView @JvmOverloads constructor(context: Context,
                                                           attrs: AttributeSet? = null,
                                                           defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr), AnimatedView {

    init {
        scaleX = 0.9f
        scaleY = 0.9f
    }

    var selectColor = 0
    var deselectColor = 0

    override fun selectAnimator(): Animator {
        return AnimatorSet()
                .apply {
                    playTogether(
                            selectScaleAnimator,
                            selectMoveAnimator)
                    addListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) = Unit
                        override fun onAnimationEnd(animation: Animator?) = Unit
                        override fun onAnimationCancel(animation: Animator?) = Unit
                        override fun onAnimationStart(animation: Animator?) {
                        }
                    })
                }
    }

    override fun deselectAnimator(): Animator {
        return AnimatorSet()
                .apply {
                    playTogether(
                            deselectScaleAnimator,
                            deselectMoveAnimator)
                }
    }

    private val selectScaleAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                //scaleAnimator(0.9f, 1.1f, 7 * KEY_FRAME_IN_MS, interpolators[0]),
                                //scaleAnimator(1.1f, 0.84f, 4 * KEY_FRAME_IN_MS, interpolators[0]),
                                scaleAnimator(0.9f, 1.0f, 4 * KEY_FRAME_IN_MS, interpolators[3]))
                    }

    private val selectMoveAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                translationYAnimator(
                                        0f,
                                        getItemOvershootTransitionYValue(context, true),
                                        7 * KEY_FRAME_IN_MS,
                                        interpolators[0]),
                                translationYAnimator(
                                        getItemOvershootTransitionYValue(context, true),
                                        getItemTransitionYValue(context, true),
                                        3 * KEY_FRAME_IN_MS,
                                        interpolators[4]))
                        startDelay = 11 * KEY_FRAME_IN_MS
                    }


    private val deselectScaleAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                scaleAnimator(1.0f, 0.9f, 4 * KEY_FRAME_IN_MS, interpolators[3])//,
                                //scaleAnimator(0.84f, 1.1f, 4 * KEY_FRAME_IN_MS, interpolators[0]),
                                //scaleAnimator(1.1f, 0.9f, 7 * KEY_FRAME_IN_MS, interpolators[0])
                        )

                    }

    private val deselectMoveAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                translationYAnimator(
                                        getItemTransitionYValue(context, true),
                                        getItemOvershootTransitionYValue(context, true),
                                        3 * KEY_FRAME_IN_MS,
                                        interpolators[4]),
                                translationYAnimator(
                                        getItemOvershootTransitionYValue(context, true),
                                        0f,
                                        7 * KEY_FRAME_IN_MS,
                                        interpolators[0]))
                        startDelay = 6 * KEY_FRAME_IN_MS
                    }

}
