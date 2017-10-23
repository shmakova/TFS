package ru.shmakova.tfs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*
import java.util.concurrent.Executors

class SecondWorkerFragment : Fragment() {

    private var listener: OnResultListener? = null
    private var result: MutableList<String> = mutableListOf()
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
                result = mutableListOf("val1", "val2", "val3")

                if (listener != null) {
                    listener!!.onResult(result)
                }

                Thread.sleep(5000)
                result.addAll(Arrays.asList("val4", "val5", "val6"))

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

    override fun onDestroy() {
        super.onDestroy()
        executorService.shutdown()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        val TAG = "SecondWorkerFragment"
    }
}
