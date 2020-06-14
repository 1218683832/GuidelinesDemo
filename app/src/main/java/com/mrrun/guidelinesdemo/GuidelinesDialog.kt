package com.mrrun.guidelinesdemo

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * 作品指引
 */
class GuidelinesDialog : Dialog {

    constructor(context: Context, guidelines1: IntArray, guidelines2: IntArray) : super(
        context,
        R.style.PopupDialog
    ) {
        setCanceledOnTouchOutside(false)
        val window = this.window
        window?.setGravity(Gravity.TOP or Gravity.LEFT)
        this.guidelines1 = guidelines1
        this.guidelines2 = guidelines2
    }

    /**
     * 指引覆盖位置点1
     */
    private val guidelines1: IntArray
    /**
     * 指引覆盖位置点1
     */
    private val guidelines2: IntArray

    /**
     * 指引覆盖覆盖层view1
     */
    private var layExcellentWorksGuidelines: View? = null
    /**
     * 指引覆盖覆盖层view2
     */
    private var laySameWorksGuidelines: View? = null
    /**
     * 顶部状态栏高度
     */
    private val statusBarHeight: Int
        private get() {
            val resources = context.resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_guidelines_layout)
        setCancelable(false)

        var content = findViewById<View>(android.R.id.content)
        content.layoutParams.width = getScreenWidth(context)

        /**
         * 定位指引覆盖层view1
         */
        layExcellentWorksGuidelines = findViewById(R.id.lay_excellent_works_guidelines)
        val imgExcellentWorksClick = findViewById<View>(R.id.img_excellent_works_click)
        val params1 = imgExcellentWorksClick.layoutParams as ConstraintLayout.LayoutParams

        params1.leftMargin = guidelines1[0] - 30
        params1.topMargin = guidelines1[1] - statusBarHeight - 35
        imgExcellentWorksClick.layoutParams = params1
        imgExcellentWorksClick.setOnClickListener {
            layExcellentWorksGuidelines?.visibility = View.GONE
            laySameWorksGuidelines?.visibility = View.VISIBLE
        }

        /**
         * 定位指引覆盖层view2
         */
        laySameWorksGuidelines = findViewById(R.id.lay_same_works_guidelines)
        val imgSameWorksClick = findViewById<View>(R.id.img_same_works_click)
        val params2 = imgSameWorksClick.layoutParams as ConstraintLayout.LayoutParams
        params2.leftMargin = guidelines2[0] - 30
        params2.topMargin = guidelines2[1] - statusBarHeight - 35
        imgSameWorksClick.layoutParams = params2
        imgSameWorksClick.setOnClickListener { dismiss() }
    }

    override fun dismiss() {
        super.dismiss()
        SharedPreferencesUtil.getInstance(context).putBoolean(spShowGuidelines, false)
    }

    companion object {
        const val spShowGuidelines = "ShowGuidelines"
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    private fun getScreenWidth(context: Context): Int {
        val dm = DisplayMetrics()
        val windowMgr =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowMgr.defaultDisplay.getRealMetrics(dm)
        // 获取宽度
        return dm.widthPixels
    }
}