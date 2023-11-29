package uz.blog.uzpay.screen.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.blog.uzpay.databinding.CategoryItmLayoutBinding
import uz.blog.uzpay.screen.model.CategoryModel
import uz.blog.uzpay.screen.utils.loadImage
interface CategoryCallBack{
    fun sendCategoryId(categoryId: CategoryModel)
}
class CategoryAdapter(val items: List<CategoryModel>, var categoryId: CategoryCallBack): RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

    inner class ItemHolder(val binding: CategoryItmLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(CategoryItmLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.binding.tvCategoryTitle.text = item.title
        holder.binding.imgCategory.loadImage(item.image)

        holder.itemView.setOnClickListener {
            categoryId.sendCategoryId(item)
        }
    }
}