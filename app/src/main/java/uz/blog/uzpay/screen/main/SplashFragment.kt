package uz.blog.uzpay.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import uz.blog.uzpay.R
import uz.blog.uzpay.databinding.FragmentSplashBinding
import uz.blog.uzpay.screen.utils.PrefUtils


class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgSplash.postDelayed({
            if (PrefUtils.getToken().isEmpty()) {
                NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_mainFragment)
            }
        }, 2000)
    }

    companion object {

        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}