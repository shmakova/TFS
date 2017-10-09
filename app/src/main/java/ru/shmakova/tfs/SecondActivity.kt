package ru.shmakova.tfs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    companion object {
        val requestCode = 1
        val secondActivityKey = "second_activity_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        editText.setText(intent.getStringExtra(secondActivityKey))

        okButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra(FirstActivity.firstActivityKey, editText.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
