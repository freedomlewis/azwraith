package com.marcosholgado.mymodule

import android.os.Bundle
import com.marcosholgado.core.ExpensiveObject
import com.marcosholgado.core.NuwaInjectorActivity
import com.marcosholgado.core.di.NuwaInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_other.*
import java.util.*
import javax.inject.Inject

class OtherActivity : NuwaInjectorActivity() {

    @Inject
    lateinit var expensiveObject: ExpensiveObject

    @Inject
    lateinit var text: String

    @Inject
    lateinit var date: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        textView.text = date.toString()
    }

    override fun getInjector(): NuwaInjector {
        return Entry
    }
}
