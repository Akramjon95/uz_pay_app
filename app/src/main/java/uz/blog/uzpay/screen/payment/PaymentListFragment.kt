package uz.blog.uzpay.screen.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import uz.blog.uzpay.databinding.FragmentPaymentListBinding
import uz.blog.uzpay.screen.add_card.AddCardViewModel
import uz.blog.uzpay.screen.model.ServicesModel
import uz.blog.uzpay.screen.utils.showMessage
import uz.blog.uzpay.screen.views.PaymentListAdapter
import uz.blog.uzpay.screen.views.PaymentListCallBack


class PaymentListFragment : Fragment(), PaymentListCallBack {
    lateinit var binding: FragmentPaymentListBinding
    private val viewModel: AddCardViewModel by activityViewModels()

    val args: PaymentListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.recyclerPayment.layoutManager = LinearLayoutManager(requireActivity())
        binding.swipe.setOnRefreshListener {
            viewModel.getPaymentList(args.categoryId)
        }
        viewModel.errorLiveData.observe(requireActivity()){
            requireActivity().showMessage(it)
        }
        viewModel.progressLiveData.observe(requireActivity()){
            binding.swipe.isRefreshing = it
        }

        viewModel.servicesModelData.observe(requireActivity()){
            binding.recyclerPayment.adapter = PaymentListAdapter(it, this)
        }

        viewModel.getPaymentList(args.categoryId)

    }


    companion object {

        @JvmStatic
        fun newInstance() = PaymentListFragment()
    }

    override fun openPaymentFragment(service_id: Int, service_name: String, service_image: String) {
        val action = PaymentListFragmentDirections.actionPaymentListFragmentToPaymentFragment(service_id.toString(), service_name, service_image)
        NavHostFragment.findNavController(this).navigate(action)
    }


}