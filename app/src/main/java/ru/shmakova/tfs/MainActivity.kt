package ru.shmakova.tfs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FirstFragment.OnFirstArgumentSendListener,
        SecondFragment.OnSecondArgumentSendListener, SignFragment.OnSignChooseListener {

    private var firstArg: Double = 0.0
    private var secondArg: Double = 0.0
    private var sign: SignFragment.Sign? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_first_arg -> backTo(FirstFragment.TAG)
                R.id.action_sign -> backTo(SignFragment.TAG)
                R.id.action_second_arg -> backTo(SecondFragment.TAG)
            }
            true
        }

        if (savedInstanceState == null) {
            val fragment = FirstFragment()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, FirstFragment.TAG)
                    .commit()
        }
    }

    override fun onFirstFragmentSend(arg: Double) {
        firstArg = arg
        val fragment = SignFragment()
        replaceFragment(fragment, SignFragment.TAG)
    }

    override fun onSecondFragmentSend(arg: Double) {
        secondArg = arg
        val fragment = ResultFragment.newInstance(getResult())
        replaceFragment(fragment, ResultFragment.TAG)
    }

    override fun onSignChoose(arg: SignFragment.Sign) {
        sign = arg
        val fragment = SecondFragment()
        replaceFragment(fragment, SecondFragment.TAG)
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment, tag)
                .addToBackStack(tag)
                .commit()
    }

    private fun backTo(tag: String) {
        val fragment = supportFragmentManager.findFragmentByTag(tag)

        if (fragment != null) {
            for (i: Int in supportFragmentManager.backStackEntryCount - 1 downTo 0) {
                val topTag = supportFragmentManager.getBackStackEntryAt(i).name
                if (topTag == tag) {
                    break
                }
                supportFragmentManager.popBackStackImmediate()
            }
        }
    }

    private fun getResult(): Double {
        var result = 0.0
        when (sign) {
            SignFragment.Sign.MINUS -> result = firstArg - secondArg
            SignFragment.Sign.PLUS -> result = firstArg + secondArg
            SignFragment.Sign.DIVIDE -> result = firstArg / secondArg
            SignFragment.Sign.MULTIPLY -> result = firstArg * secondArg
        }
        return result
    }
}
