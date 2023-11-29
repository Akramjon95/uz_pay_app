package uz.blog.uzpay.screen.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.blog.uzpay.R
import uz.blog.uzpay.databinding.ActivitySplashBinding
import uz.blog.uzpay.screen.main.MainActivity
import uz.blog.uzpay.screen.utils.PrefUtils

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}