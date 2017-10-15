package ru.shmakova.tfs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sign.*

class SignFragment : Fragment() {

    enum class Sign {
        PLUS, MINUS, DIVIDE, MULTIPLY
    }

    private var listener: OnSignChooseListener? = null

    interface OnSignChooseListener {
        fun onSignChoose(arg: Sign)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_sign, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        minusButton.setOnClickListener({
            listener!!.onSignChoose(Sign.MINUS)
        })
        plusButton.setOnClickListener({
            listener!!.onSignChoose(Sign.PLUS)
        })
        divideButton.setOnClickListener({
            listener!!.onSignChoose(Sign.DIVIDE)
        })
        multiplyButton.setOnClickListener({
            listener!!.onSignChoose(Sign.MULTIPLY)
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnSignChooseListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnSignChooseListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        val TAG = "SignFragment"
    }
}
