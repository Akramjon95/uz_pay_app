package uz.blog.uzpay.screen.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import uz.blog.uzpay.R
import uz.blog.uzpay.databinding.FragmentLoginBinding
import uz.blog.uzpay.screen.model.request.LoginRequest
import uz.blog.uzpay.screen.utils.PrefUtils
import uz.blog.uzpay.screen.utils.showMessage


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.errorLiveData.observe(this){
            requireActivity().showMessage(it)
        }
        viewModel.progressLiveData.observe(this){
            binding.flProgress.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loginLiveData.observe(this){
            if (it.status == "accept"){
                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_mainFragment)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnRegistration.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment)
        }
        
        binding.btnSend.setOnClickListener {
                viewModel.getLogin(LoginRequest(
                    binding.edPhone.text.toString(),
                    binding.edPassword.text.toString()
                ))
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}