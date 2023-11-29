package uz.blog.uzpay.screen.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import uz.blog.uzpay.R
import uz.blog.uzpay.databinding.FragmentPaymentBinding
import uz.blog.uzpay.screen.main.viewpager.ViewPagerAdapter
import uz.blog.uzpay.screen.model.request.PaymentRequest
import uz.blog.uzpay.screen.utils.PrefUtils
import uz.blog.uzpay.screen.utils.loadImage
import uz.blog.uzpay.screen.utils.showMessage


class PaymentFragment : Fragment() {
    lateinit var binding: FragmentPaymentBinding
    private val viewModel: PaymentViewModel by activityViewModels()
    val args: PaymentFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.errorLiveData.observe(this){
            requireActivity().showMessage(it)
        }

        viewModel.progressLiveData.observe(this){
            binding.flProgress.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.cardData.observe(this){
            binding.viewPager.adapter = ViewPagerAdapter(it)
        }
        viewModel.paymentData.observe(this){
            binding.mainContainer.visibility = View.GONE
            binding.addSuccessContainer.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edServiceName.isEnabled = false
        binding.viewPager.setPageTransformer { page, position ->
            val MIN_SCALE = 0.75f
            val pageWidth = page.width
            if (position < -1) {
                page.alpha = 0f
            } else if (position <= 0) {
                page.alpha = 1f
                page.translationX = 0f
                page.scaleX = 1f
                page.scaleY = 1f
            } else if (position <= 1) {
                page.alpha = 1 - position
                page.translationX = pageWidth * -position
                val scaleFactor = (MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position)))
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
            } else {
                page.alpha = 0f
            }
        }

        binding.edServiceName.setText(args.serviceName)
        binding.imgService.loadImage(args.serviceImage.toString())

        viewModel.getCards()
        var card_id = 0
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                card_id = position
                super.onPageSelected(position)
            }
        })

        binding.btnPay.setOnClickListener {
            viewModel.payment(PaymentRequest(
                args.serviceId.toString().toInt(),
                "12345678",
                binding.edAmount.text.toString().toDouble(),
                card_id
            ))
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = PaymentFragment()
    }
}