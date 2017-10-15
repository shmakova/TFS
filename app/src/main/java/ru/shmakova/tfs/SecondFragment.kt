package ru.shmakova.tfs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    private var listener: OnSecondArgumentSendListener? = null

    interface OnSecondArgumentSendListener {
        fun onSecondFragmentSend(arg: Double)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendButton.setOnClickListener({
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                listener!!.onSecondFragmentSend(text.toDouble())
            }
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnSecondArgumentSendListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnSecondArgumentSendListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        val TAG = "SecondFragment"
    }
}
