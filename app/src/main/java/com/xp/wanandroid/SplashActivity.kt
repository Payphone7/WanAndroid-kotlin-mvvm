package com.xp.wanandroid

import android.content.Intent
import android.os.CountDownTimer
import com.wanandroid.xp_commom.base.BaseActivity
import com.xp.wanandroid.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private lateinit var mCountDownTimer: CountDownTimer

    override fun init() {
        mCountDownTimer = object : CountDownTimer(4000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val stringBuffer = StringBuffer((millisUntilFinished / 1000).toString())
                stringBuffer.append("秒后跳过")
                mBinding.tvSkip.text = stringBuffer.toString()
            }

            override fun onFinish() {
                startMainActivity()
            }

        }.start()
    }


    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        mCountDownTimer.cancel()
        finish()
    }

    override fun observer() {
        mBinding.tvSkip.setOnClickListener{
            startMainActivity()
        }
    }

    override fun getLayoutId() = R.layout.activity_splash

    override fun onDestroy() {
        super.onDestroy()
        mCountDownTimer.cancel()
    }
}