package com.example.ecommerceapp2

import ProductAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp2.databinding.ProductsFragmentBinding

class ProductFragment: Fragment() {
    private lateinit var productFragmentBinding: ProductsFragmentBinding
    private lateinit var productAdapter: ProductAdapter
    private val products : ArrayList<Product> = arrayListOf()
    private val filteredProducts :ArrayList<Product> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productFragmentBinding = ProductsFragmentBinding.inflate(layoutInflater)
        return productFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        products.add(Product(1, "Laptop", "Acer Aspire Lite 12th Gen Intel Core i3-1215U Premium Metal Laptop (Windows 11 Home/8 GB RAM/512GB SSD) AL15-52, 39.62cm (15.6\") Full HD Display, Metal Body, Steel Gray, 1.59 Kg", 55999.0, "https://example.com/image1.jpg"))
        products.add(Product(2, "SmartPhone", "Pixel 7a 5G (Sea, 8GB RAM, 128GB Storage)", 29990.0, "https://example.com/image2.jpg"))
        products.add(Product(3, "Charger", "Samsung Original 25W Type-C Travel Adaptor without Cable, White", 1550.0, "https://example.com/image3.jpg"))
        products.add(Product(4, "SmartWatch", "Fitbit Inspire 3 Health & Fitness Tracker (Midnight Zen/Black) with 6-Month Premium Membership", 999.9, "https://example.com/image4.jpg"))
        products.add(Product(5, "Bag", "American Tourister Valex 28 Ltrs Large Laptop Backpack", 550.0, "https://example.com/image5.jpg"))
        products.add(Product(6, "Earphone", "Sony MDR-EX155AP in-Ear Wired Headphones with Mic (Black)", 1200.0, "https://example.com/image6.jpg"))
        products.add(Product(7, "HeadPhone", "Bose Quietcomfort 45 Bluetooth Wireless Over Ear Headphones with Mic Noise Cancelling - Triple Black\n", 2599.0, "https://example.com/image7.jpg"))
        products.add(Product(8, "Keyboard", "Dell KB216-Black Multimedia Wired Keyboard with USB Interface, Plunger Keys Technology and Chiclet Key Style, Hot Key-Volume, Mute, Play/Pause, Backward, Forward, Warranty 1 Year.", 499.0, "https://example.com/image8.jpg"))
        products.add(Product(9, "mouse", "HP M120 Wireless Mouse, USB-A Nano Dongle, 2.4 Ghz Wireless Connection, 6 Buttons, Up to 1600 Dpi, Optical Sensor, Ergonomic Design, 12-Month Battery Life, 3-Year Warranty, 60G±5%, Black, 7J4G4Aa", 299.0, "https://example.com/image9.jpg"))
        products.add(Product(10, "speaker", "boAt Stone 352 Bluetooth Speaker with 10W RMS Stereo Sound, IPX7 Water Resistance, TWS Feature, Up to 12H Total Playtime, Multi-Compatibility Modes and Type-C Charging(Raging Black)", 3549.0, "https://example.com/image10.jpg"))

        filteredProducts.addAll(products)
        productAdapter = ProductAdapter(filteredProducts,activity as MainActivity)
        productFragmentBinding.recyclerView.adapter = productAdapter
        productFragmentBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
    fun filterProducts(query: String?) {
        val filteredList = products.filter {
            it.title.contains(query!!,ignoreCase = true)
        }
        filteredProducts.clear()
        filteredProducts.addAll(filteredList)
        productAdapter.notifyDataSetChanged()
    }

}