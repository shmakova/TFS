package ru.shmakova.tfs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), WorkerFragment.OnResultListener {

    private var workerFragment: WorkerFragment? = null
    private var listAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openActivityButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        with(recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            listAdapter = ListAdapter()
            adapter = listAdapter
        }

        workerFragment = supportFragmentManager.findFragmentByTag(WorkerFragment.TAG) as? WorkerFragment

        if (workerFragment == null && savedInstanceState == null) {
            workerFragment = WorkerFragment()
            supportFragmentManager.beginTransaction().add(workerFragment, WorkerFragment.TAG).commit()
        }
    }

    override fun onStop() {
        super.onStop()
        workerFragment!!.cancel()
    }

    override fun onResult(list: List<String>) {
        listAdapter!!.setItems(list)
    }
}
