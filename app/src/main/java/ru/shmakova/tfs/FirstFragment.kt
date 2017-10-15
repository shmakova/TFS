package ru.shmakova.tfs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    private var listener: OnFirstArgumentSendListener? = null

    interface OnFirstArgumentSendListener {
        fun onFirstFragmentSend(arg: Double)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendButton.setOnClickListener({
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                listener!!.onFirstFragmentSend(text.toDouble())
            }
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFirstArgumentSendListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFirstArgumentSendListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        val TAG = "FirstFragment"
    }
}
