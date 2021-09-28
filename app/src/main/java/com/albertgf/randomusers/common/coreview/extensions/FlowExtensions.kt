package com.albertgf.randomusers.common.coreview.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlin.reflect.KSuspendFunction0

fun AppCompatActivity.collectFlow(lambda: KSuspendFunction0<Unit>) {
    lifecycleScope.launchWhenStarted {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            lambda()
        }
    }
}