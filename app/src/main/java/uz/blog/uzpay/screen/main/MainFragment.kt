package uz.blog.uzpay.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.orhanobut.hawk.Hawk
import uz.blog.uzpay.R
import uz.blog.uzpay.databinding.FragmentMainBinding
import uz.blog.uzpay.screen.main.viewpager.ViewPagerAdapter
import uz.blog.uzpay.screen.model.CategoryModel
import uz.blog.uzpay.screen.utils.PrefUtils
import uz.blog.uzpay.screen.utils.showMessage
import uz.blog.uzpay.screen.views.CategoryAdapter
import uz.blog.uzpay.screen.views.CategoryCallBack
import uz.blog.uzpay.screen.views.PaymentHistoryAdapter


class MainFragment : Fragment(), CategoryCallBack {
    lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.errorLiveData.observe(this){
             requireActivity().showMessage(it)
        }

        viewModel.progressLiveData.observe(this){
            binding.swipe.isRefreshing = it
        }

        viewModel.categoryData.observe(this){
            binding.recycleCategory.adapter = CategoryAdapter(it, this)
        }

        viewModel.cardData.observe(this){
            binding.viewPager.adapter = ViewPagerAdapter(it)
        }

        viewModel.paymentHistoryData.observe(this){
            binding.recyclePayment.adapter = PaymentHistoryAdapter(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipe.setOnRefreshListener {
            loadData()
        }

//        binding.viewPager.clipToPadding = false
//        binding.viewPager.clipChildren = false
//        binding.viewPager.offscreenPageLimit = 2
//        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
//
//        val compositePageTransformer = CompositePageTransformer()
//        compositePageTransformer.addTransformer(MarginPageTransformer(40))
//        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
//            val r = 1 - Math.abs(position)
//            page.scaleY = 0.95f + r * 0.05f
//        })
//        binding.viewPager.setPageTransformer(compositePageTransformer)
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

        binding.recycleCategory.layoutManager = GridLayoutManager(requireActivity(), 3)
        binding.recyclePayment.layoutManager = LinearLayoutManager(requireActivity())
        loadData()

        binding.imgPayment.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addCardFragment)
        }
    }

    fun loadData(){
        viewModel.getCategories()
        viewModel.getCards()
        viewModel.getPaymentHistoryList()
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun sendCategoryId(categoryId: CategoryModel) {
        val action = MainFragmentDirections.actionMainFragmentToPaymentListFragment(categoryId.id)
        NavHostFragment.findNavController(this).navigate(action)
    }


}