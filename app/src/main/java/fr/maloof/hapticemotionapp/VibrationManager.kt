package fr.maloof.hapticemotionapp

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log

class VibrationManager(private val context: Context) {

    private val vibrator: Vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    private var currentIndex = 0

    private val vibrationList: List<() -> Unit> = listOf(
        { keyboardReleaseFeedback() },
        { virtualKeyReleaseFeedback() },
        { clockTickFeedback() },
        { textHandleMoveFeedback() },
        { gestureEndFeedback() },
        { virtualKeyFeedback() },
        { keyboardPressFeedback() },
        { dragStartFeedback() },
        { contextClickFeedback() },
        { gestureStartFeedback() },
        { confirmFeedback() },
        { longPressFeedback() },
        { rejectFeedback() },
        { toggleOnFeedback() },
        { toggleOffFeedback() },
        { gestureThresholdActivateFeedback() },
        { gestureThresholdDeactivateFeedback() },
        { keyboardTapFeedback() },
        { segmentTickFeedback() },
        { segmentFrequentTickFeedback() }
    )

    fun playNextVibration() {
        if (currentIndex >= vibrationList.size) {
            currentIndex = 0
        }

        Log.d("HAPTIC_UTIL", "▶️ Playing vibration #${currentIndex + 1}")
        vibrationList[currentIndex].invoke()
        currentIndex++
    }

    fun vibratePattern(pattern: LongArray) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect = VibrationEffect.createWaveform(pattern, -1)
            vibrator.vibrate(effect)
        } else {
            vibrator.vibrate(pattern, -1)
        }
    }

    fun vibrateOneShot(milliseconds: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect = VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(effect)
        } else {
            vibrator.vibrate(longArrayOf(0, milliseconds), -1)
        }
    }

    // === Feedback methods ===

    fun keyboardReleaseFeedback() {
        vibratePattern(longArrayOf(0, 50, 50, 100))
    }

    fun virtualKeyReleaseFeedback() {
        vibratePattern(longArrayOf(0, 30, 30, 70))
    }

    fun clockTickFeedback() {
        vibratePattern(longArrayOf(0, 10, 20, 10))
    }

    fun textHandleMoveFeedback() {
        vibratePattern(longArrayOf(0, 40, 40, 80))
    }

    fun gestureEndFeedback() {
        vibratePattern(longArrayOf(0, 60, 60, 120))
    }

    fun virtualKeyFeedback() {
        vibrateOneShot(30)
    }

    fun keyboardPressFeedback() {
        vibrateOneShot(50)
    }

    fun dragStartFeedback() {
        vibratePattern(longArrayOf(0, 100, 50, 100))
    }

    fun contextClickFeedback() {
        vibrateOneShot(100)
    }

    fun gestureStartFeedback() {
        vibratePattern(longArrayOf(0, 70, 70, 140))
    }

    fun confirmFeedback() {
        vibrateOneShot(200)
    }

    fun longPressFeedback() {
        vibrateOneShot(400)
    }

    fun rejectFeedback() {
        vibratePattern(longArrayOf(0, 50, 50, 50, 50, 50))
    }

    fun toggleOnFeedback() {
        vibrateOneShot(150)
    }

    fun toggleOffFeedback() {
        vibrateOneShot(100)
    }

    fun gestureThresholdActivateFeedback() {
        vibratePattern(longArrayOf(0, 200, 50, 200))
    }

    fun gestureThresholdDeactivateFeedback() {
        vibratePattern(longArrayOf(0, 200, 50, 100))
    }

    fun keyboardTapFeedback() {
        vibrateOneShot(20)
    }

    fun segmentTickFeedback() {
        vibrateOneShot(10)
    }

    fun segmentFrequentTickFeedback() {
        vibrateOneShot(5)
    }
}
