package uz.blog.uzpay.screen.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import uz.blog.uzpay.R
import uz.blog.uzpay.databinding.FragmentRegisterBinding
import uz.blog.uzpay.screen.model.request.RegisterRequest
import uz.blog.uzpay.screen.utils.showMessage


class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegistration.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_loginFragment)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            requireActivity().showMessage(it)
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner){
            binding.flProgress.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.registerLiveData.observe(viewLifecycleOwner){
            requireActivity().showMessage(it.status)
            NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_mainFragment)
        }

       binding.btnSend.setOnClickListener {
           viewModel.sendRegister(
               RegisterRequest(
                   binding.edName.text.toString(),
                   binding.edPhone.text.toString(),
                   binding.edPassword.text.toString()
               )
           )
       }
    }

    companion object {

        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}