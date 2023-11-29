package uz.blog.uzpay.screen.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.blog.uzpay.databinding.CategoryItmLayoutBinding
import uz.blog.uzpay.databinding.PaymentHistoryItemLayoutBinding
import uz.blog.uzpay.screen.model.CategoryModel
import uz.blog.uzpay.screen.model.PaymentHistoryModel
import uz.blog.uzpay.screen.utils.loadImage
import uz.blog.uzpay.screen.utils.setOffSetNumbers

class PaymentHistoryAdapter(val items: List<PaymentHistoryModel>): RecyclerView.Adapter<PaymentHistoryAdapter.ItemHolder>() {

    inner class ItemHolder(val binding: PaymentHistoryItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(PaymentHistoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.binding.tvCardNumber.text = item.from_card.setOffSetNumbers()
        holder.binding.tvDate.text = item.date
        holder.binding.tvSumma.text = item.summa.toString() + " UZS"
    }
}