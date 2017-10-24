package ru.shmakova.tfs

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WorkerFragment : Fragment() {

    private var listener: OnResultListener? = null
    private var result: List<String> = emptyList()
    private val handler = Handler()
    private var handlerThread: WorkerThread? = null

    interface OnResultListener {
        fun onResult(list: List<String>)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!result.isEmpty()) {
            if (listener != null) {
                listener!!.onResult(result)
            }
        } else {
            handlerThread = WorkerThread("WorkerThread")
            handlerThread!!.postTask(Runnable {
                Thread.sleep(5000)
                result = listOf("1 val1", "1 val2", "1 val3")

                handler.post {
                    if (listener != null) {
                        listener!!.onResult(result)
                    }
                }
            })
        }
        return null
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnResultListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnResultListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    fun cancel() {
        if (handlerThread != null) {
            handlerThread!!.cancel()
        }
        listener = null
    }

    companion object {
        val TAG = "WorkerFragment"
    }
}
