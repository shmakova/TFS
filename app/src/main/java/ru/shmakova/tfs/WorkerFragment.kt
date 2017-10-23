package ru.shmakova.tfs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*
import java.util.concurrent.Executors

class WorkerFragment : Fragment() {

    private var listener: OnResultListener? = null
    private var result: List<String> = emptyList()
    private val executorService = Executors.newSingleThreadExecutor()

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
            executorService.execute({
                Thread.sleep(5000)
                result = Arrays.asList("val1", "val2", "val3")

                if (listener != null) {
                    listener!!.onResult(result)
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

    companion object {
        val TAG = "WorkerFragment"
    }
}
