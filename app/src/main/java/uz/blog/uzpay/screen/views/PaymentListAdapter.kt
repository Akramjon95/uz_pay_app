package uz.blog.uzpay.screen.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.blog.uzpay.databinding.PaymentListItemLayoutBinding
import uz.blog.uzpay.screen.model.ServicesModel
import uz.blog.uzpay.screen.utils.loadImage

interface PaymentListCallBack{
    fun openPaymentFragment(service_id: Int, service_name: String, service_image: String)
}
class PaymentListAdapter(val items: List<ServicesModel>, val callback: PaymentListCallBack): RecyclerView.Adapter<PaymentListAdapter.ItemHolder>() {

    inner class ItemHolder(val binding: PaymentListItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(PaymentListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.binding.tvTitle.text = item.name
        holder.binding.imgCompany.loadImage(item.image)

        holder.itemView.setOnClickListener {
            callback.openPaymentFragment(item.id, item.name, item.image)
        }
    }
}