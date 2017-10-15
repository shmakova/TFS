package ru.shmakova.tfs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment() {

    private var result: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            result = arguments.getDouble(ARG_RESULT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultText.text = result.toString()
    }

    companion object {
        private val ARG_RESULT = "ARG_RESULT"
        val TAG = "ResultFragment"

        fun newInstance(result: Double): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putDouble(ARG_RESULT, result)
            fragment.arguments = args
            return fragment
        }
    }
}
