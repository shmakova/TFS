package ru.shmakova.tfs

import android.os.Handler
import android.os.HandlerThread

class WorkerThread(name: String) {

    private val handlerThread = HandlerThread(name)
    private val handler: Handler

    init {
        handlerThread.start()
        handler = Handler(handlerThread.looper)
    }

    fun postTask(task: Runnable) {
        handler.post(task)
    }

    fun cancel() {
        handlerThread.quit()
    }
}