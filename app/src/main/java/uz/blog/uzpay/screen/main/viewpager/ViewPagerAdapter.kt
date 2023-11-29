package uz.blog.uzpay.screen.main.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.blog.uzpay.databinding.ViewPagerItemLayoutBinding
import uz.blog.uzpay.screen.main.MainFragment
import uz.blog.uzpay.screen.model.CardModel
import uz.blog.uzpay.screen.utils.setOffSetNumbers

class ViewPagerAdapter(var cardList: List<CardModel>): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ViewPagerItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(ViewPagerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return cardList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val item = cardList[position]
        holder.binding.tvCardNumber.text = item.card_number.setOffSetNumbers()
        holder.binding.tvCardDate.text = item.validity_period
        holder.binding.tvCardName.text = item.name
    }


}