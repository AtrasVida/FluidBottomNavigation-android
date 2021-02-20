package com.tenclouds.fluidbottomnavigation.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.tenclouds.fluidbottomnavigation.KEY_FRAME_IN_MS
import com.tenclouds.fluidbottomnavigation.extension.*

internal class IconView @JvmOverloads constructor(context: Context,
                                                  attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = 0)
    : AppCompatImageView(context, attrs, defStyleAttr), AnimatedView {

    init {
        scaleX = 0.9f
        scaleY = 0.9f
    }

    var selectColor = 0
    var deselectColor = 0

    var isInsideMode = false

    override fun selectAnimator() =
            AnimatorSet()
                    .apply {
                        if (isInsideMode) playTogether(selectScaleAnimator, selectTintAnimator)
                        else
                            playTogether(selectScaleAnimator, selectMoveAnimator, selectTintAnimator)

                        addListener(object : Animator.AnimatorListener {
                            override fun onAnimationRepeat(animation: Animator?) = Unit
                            override fun onAnimationEnd(animation: Animator?) = Unit
                            override fun onAnimationCancel(animation: Animator?) = Unit
                            override fun onAnimationStart(animation: Animator?) {
                                deselectTintAnimator.cancel()
                                setTintColor(selectColor)
                            }
                        })
                    }


    override fun deselectAnimator() =
            AnimatorSet()
                    .apply {
                        if (isInsideMode) playTogether(deselectScaleAnimator, deselectTintAnimator)
                        else
                            playTogether(deselectScaleAnimator, deselectMoveAnimator, deselectTintAnimator)
                    }


    private val selectScaleAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                scaleAnimator(0.9f, 1.1f, 7 * KEY_FRAME_IN_MS, interpolators[0]),
                                scaleAnimator(1.1f, 0.84f, 4 * KEY_FRAME_IN_MS, interpolators[0]),
                                scaleAnimator(0.84f, 0.9f, 4 * KEY_FRAME_IN_MS, interpolators[3]))
                    }

    private val selectMoveAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                translationYAnimator(
                                        0f,
                                        getItemOvershootTransitionYValue(context, isInsideMode),
                                        7 * KEY_FRAME_IN_MS,
                                        interpolators[0]),
                                translationYAnimator(
                                        getItemOvershootTransitionYValue(context, isInsideMode),
                                        getItemTransitionYValue(context, isInsideMode),
                                        3 * KEY_FRAME_IN_MS,
                                        interpolators[4]))
                        startDelay = 11 * KEY_FRAME_IN_MS
                    }

    private val selectTintAnimator by lazy {
        AnimatorSet()
                .apply {
                    play(tintAnimator(
                            deselectColor,
                            selectColor,
                            3 * KEY_FRAME_IN_MS))
                }
    }

    private val deselectScaleAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                scaleAnimator(0.9f, 0.84f, 4 * KEY_FRAME_IN_MS, interpolators[3]),
                                scaleAnimator(0.84f, 1.1f, 4 * KEY_FRAME_IN_MS, interpolators[0]),
                                scaleAnimator(1.1f, 0.9f, 7 * KEY_FRAME_IN_MS, interpolators[0]))
                    }

    private val deselectMoveAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                translationYAnimator(
                                        getItemTransitionYValue(context, isInsideMode),
                                        getItemOvershootTransitionYValue(context, isInsideMode),
                                        3 * KEY_FRAME_IN_MS,
                                        interpolators[4]),
                                translationYAnimator(
                                        getItemOvershootTransitionYValue(context, isInsideMode),
                                        0f,
                                        7 * KEY_FRAME_IN_MS,
                                        interpolators[0]))
                        startDelay = 6 * KEY_FRAME_IN_MS
                    }

    private val deselectTintAnimator by lazy {
        AnimatorSet()
                .apply {
                    play(tintAnimator(
                            selectColor,
                            deselectColor,
                            3 * KEY_FRAME_IN_MS))
                    startDelay = 19 * KEY_FRAME_IN_MS
                }
    }
}
