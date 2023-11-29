package uz.blog.uzpay.screen.add_card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import uz.blog.uzpay.R
import uz.blog.uzpay.databinding.FragmentAddCardBinding
import uz.blog.uzpay.screen.model.request.CardRequest
import uz.blog.uzpay.screen.utils.showMessage


class AddCardFragment : Fragment() {
    lateinit var binding: FragmentAddCardBinding
    private val viewModel: AddCardViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.errorLiveData.observe(this){
            requireActivity().showMessage(it)
        }
        viewModel.progressLiveData.observe(this){
            binding.flProgress.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.addToCardData.observe(this){
                if (it.card_number == binding.edCardNumber.text.toString()){
                    binding.mainCardContainer.visibility = View.GONE
                    binding.btnAddToCard.visibility = View.GONE
                    binding.addSuccessContainer.visibility = View.VISIBLE
                }
        }

        viewModel.successAdedData.observe(this){
            binding.addCardLayout.visibility = if (it) View.GONE else View.VISIBLE
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.btnAddToCard.setOnClickListener {
            viewModel.addToCard(CardRequest(
                binding.edCardName.text.toString(),
                binding.edCardNumber.text.toString(),
                binding.edCardDate.text.toString()
            ))
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddCardFragment()
    }
}