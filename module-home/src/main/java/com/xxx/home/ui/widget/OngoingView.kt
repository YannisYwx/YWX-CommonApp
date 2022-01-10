package com.xxx.home.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.xxx.home.R
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Author: YWX
 * Date: 2021/11/10 11:42
 * Description:
 */
class OngoingView : ConstraintLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private lateinit var dot1: View
    private lateinit var dot2: View
    private lateinit var dot3: View
    private lateinit var dot4: View

    var isOngoing = false

    private var scheduledExecutorService: ScheduledExecutorService? = null

    private var count = 0

    private lateinit var dots: List<View>

    init {
        View.inflate(this.context, R.layout.home_view_ongoing, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        dot1 = findViewById(R.id.dot1)
        dot2 = findViewById(R.id.dot2)
        dot3 = findViewById(R.id.dot3)
        dot4 = findViewById(R.id.dot4)
        dots = listOf<View>(dot1, dot2, dot3, dot4)
    }

    fun start() {
        isOngoing = true
        count = 0
        scheduledExecutorService = Executors.newScheduledThreadPool(1)
        scheduledExecutorService?.scheduleAtFixedRate(Runnable {
            post {
                count++
                setDotState(count)
            }

        }, 0, 300, TimeUnit.MILLISECONDS)
    }

    fun startOrStop() {
        if (isOngoing) {
            stop()
        } else {
            start()
        }
    }

    fun stop() {
        isOngoing = false
        count = 0
        scheduledExecutorService?.shutdown()
        scheduledExecutorService = null
        setDotState(4)
    }

    fun reset() {
        isOngoing = false
        count = 0
        scheduledExecutorService?.shutdown()
        scheduledExecutorService = null
        setDotState(0)
    }

    private fun setDotState(state: Int) {
        val blueDotSize = state % 5
        for (i in 0..3) {
            val dot = dots[i]
            if (i < blueDotSize) {
                dot.setBackgroundResource(R.drawable.dot_blue)
            } else {
                dot.setBackgroundResource(R.drawable.dot_light_blue)
            }
        }
    }

}