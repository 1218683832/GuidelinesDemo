package com.mrrun.guidelinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initGuidelines()
    }

    private fun initGuidelines() {
        tab1.postDelayed({
            var needShowGuidelines = SharedPreferencesUtil.getInstance(applicationContext)
                .getBoolean(GuidelinesDialog.spShowGuidelines, true)
            if (!needShowGuidelines) {
                //指引
                val guidelines1 = IntArray(2)
                tab2.getLocationOnScreen(guidelines1)
                //指引
                val guidelines2 = IntArray(2)
                tab3.getLocationOnScreen(guidelines2)
                var worksGuidelinesDialog = GuidelinesDialog(this, guidelines1, guidelines2)
                worksGuidelinesDialog?.show()
            }
        }, TimeUnit.MILLISECONDS.toMillis(500))
    }
}
