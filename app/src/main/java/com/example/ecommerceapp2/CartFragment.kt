package com.example.ecommerceapp2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp2.databinding.CartFragmentBinding

class CartFragment: Fragment() {
    private lateinit var cartFragmentBinding: CartFragmentBinding
    private lateinit var cartAdapter: CartAdapter
    private var cartItems: ArrayList<Product> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartFragmentBinding = CartFragmentBinding.inflate(inflater, container, false)
        return cartFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            cartItems = it.getParcelableArrayList("cart") ?: arrayListOf()
        }
        cartAdapter = CartAdapter(cartItems)
        cartFragmentBinding.cartsRecyclerView.adapter = cartAdapter
        cartFragmentBinding.cartsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        cartFragmentBinding.totalPriceTextView.text = "Total: $$totalPrice"
    }
}
