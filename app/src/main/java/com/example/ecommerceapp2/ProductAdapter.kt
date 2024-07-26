import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp2.Product
import com.example.ecommerceapp2.databinding.ProductItemBinding

class ProductAdapter(
    private val products: ArrayList<Product>,
    private val listener: onItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    interface onItemClickListener {
        fun onItemClick(product: Product)
    }

    inner class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(products[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.product = products[position]
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
