package com.example.ecommerceapp2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecommerceapp2.databinding.ProductDetailsFragmentBinding

class ProductDetailsFragment: Fragment() {

    private lateinit var productDetailsFragmentBinding: ProductDetailsFragmentBinding
    private val product: Product? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productDetailsFragmentBinding = ProductDetailsFragmentBinding.inflate(layoutInflater)
        return productDetailsFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = arguments?.getSerializable("product") as? Product
        product?.let {
            productDetailsFragmentBinding.txtProductPriceDetail.text = it.price.toString()
            productDetailsFragmentBinding.txtProductTitleDetail.text = it.title
            productDetailsFragmentBinding.txtProductDescriptionDetail.text = it.description
            productDetailsFragmentBinding.imgProductDetail.setImageResource(R.drawable.ic_launcher_foreground)


            productDetailsFragmentBinding.btnAddToCart.setOnClickListener {
                val quantity = productDetailsFragmentBinding.edtQuantity.text.toString().toIntOrNull() ?: 1
                product.quantity = quantity
                val activity = requireActivity() as MainActivity
                activity.addToCart(product)
            }
        }
    }

}