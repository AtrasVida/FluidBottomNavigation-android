package com.tenclouds.fluidbottomnavigation.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.tenclouds.fluidbottomnavigation.KEY_FRAME_IN_MS
import com.tenclouds.fluidbottomnavigation.extension.interpolators
import com.tenclouds.fluidbottomnavigation.extension.scaleAnimator
import com.tenclouds.fluidbottomnavigation.extension.translationYAnimator

internal class CircleView @JvmOverloads constructor(context: Context,
                                                    attrs: AttributeSet? = null,
                                                    defStyleAttr: Int = 0)
    : AppCompatImageView(context, attrs, defStyleAttr), AnimatedView {

    init {
        scaleY = 0f
        scaleX = 0f
    }

    var isInsideMode = false

    override fun selectAnimator(): Animator {
        return AnimatorSet().apply {
            playTogether(
                    selectScaleAnimator(),
                    selectMoveAnimator())
        }
    }

    override fun deselectAnimator(): Animator {
        return AnimatorSet()
                .apply {
                    playTogether(
                            deselectScaleAnimator(),
                            deselectMoveAnimator())
                }
    }

    private fun selectScaleAnimator() =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                //scaleAnimator(0.0f, 1.0f, 7 * KEY_FRAME_IN_MS, interpolators[0]),
                                //scaleAnimator(1.0f, 0.33f, 4 * KEY_FRAME_IN_MS, interpolators[2]),
                                //scaleAnimator(0.33f, 1.2f, 7 * KEY_FRAME_IN_MS, interpolators[1]),
                                //scaleAnimator(1.0f, 0.8f, 7 * KEY_FRAME_IN_MS, interpolators[1]),
                                //scaleAnimator(1.2f, 0.8f, 3 * KEY_FRAME_IN_MS, interpolators[1]),
                                scaleAnimator(0.0f, if (isInsideMode) 1.1f else 1.0f, 3 * KEY_FRAME_IN_MS, interpolators[1]))
                    }

    private fun selectMoveAnimator() =
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

    private fun deselectScaleAnimator() =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                //scaleAnimator(1.0f, 0.8f, 3 * KEY_FRAME_IN_MS, interpolators[1]),
                                //scaleAnimator(0.8f, 1.2f, 3 * KEY_FRAME_IN_MS, interpolators[1]),
                                //scaleAnimator(1.2f, 0.33f, 7 * KEY_FRAME_IN_MS, interpolators[1]),
                                //scaleAnimator(0.33f, 1.0f, 6 * KEY_FRAME_IN_MS, interpolators[2]),
                                //scaleAnimator(1.0f, 0.0f, 7 * KEY_FRAME_IN_MS, interpolators[0]))
                                scaleAnimator(if (isInsideMode) 1.1f else 1.0f, 0.0f, 7 * KEY_FRAME_IN_MS, interpolators[0]))
                    }

    private fun deselectMoveAnimator() =
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
}
