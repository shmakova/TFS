package ru.shmakova.tfs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(), SecondWorkerFragment.OnResultListener {

    private var workerFragment: SecondWorkerFragment? = null
    private var listAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        with(recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SecondActivity)
            listAdapter = ListAdapter()
            adapter = listAdapter
        }

        workerFragment = supportFragmentManager.findFragmentByTag(SecondWorkerFragment.TAG) as? SecondWorkerFragment

        if (workerFragment == null && savedInstanceState == null) {
            workerFragment = SecondWorkerFragment()
            supportFragmentManager.beginTransaction().add(workerFragment, SecondWorkerFragment.TAG).commit()
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        remove()
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            remove()
        }
    }

    private fun remove() {
        supportFragmentManager.beginTransaction().remove(workerFragment).commit();
    }

    override fun onResult(list: List<String>) {
        runOnUiThread {
            listAdapter!!.setItems(list)
        }
    }
}
