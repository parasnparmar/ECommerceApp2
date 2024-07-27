package com.example.ecommerceapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp2.databinding.CartItemBinding

class CartAdapter(private val cartItems: ArrayList<Product>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    interface CartUpdateListner{
        fun onCartUpdate()
    }

    inner class CartViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        val product = cartItems[position]
        holder.binding.product = product

        holder.binding.btnIncrease.setOnClickListener {
            product.quantity++
            notifyItemChanged(position)
            holder.binding.productQuantityTextView.text = product.quantity.toString()
        }

        holder.binding.btnDecrease.setOnClickListener {
            if (product.quantity > 1) {
                product.quantity--
                holder.binding.productQuantityTextView.text = product.quantity.toString()
                notifyItemChanged(position)
            }
        }
        holder.binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Remove Product")
                .setMessage("Are you sure you want to remove this product from the cart?")
                .setPositiveButton("Yes") { _, _ ->
                    cartItems.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}