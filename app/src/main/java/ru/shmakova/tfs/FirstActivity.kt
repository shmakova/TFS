package ru.shmakova.tfs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first.*


class FirstActivity : AppCompatActivity() {
    companion object {
        val firstActivityKey = "first_activity_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        sendButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(SecondActivity.secondActivityKey, editText.text.toString())
            startActivityForResult(intent, SecondActivity.requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SecondActivity.requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                editText.setText(data?.getStringExtra(firstActivityKey))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
