package com.example.ecommerceapp2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ecommerceapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProductAdapter.onItemClickListener {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var productFragment: ProductFragment
    private val cart = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        if (savedInstanceState == null) {
            productFragment = ProductFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, productFragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_list,menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_serach)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let {
                    productFragment.filterProducts(it)
                    return true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText.let {
                    productFragment.filterProducts(it)
                    return true
                }
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_cart -> {
                openCartFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

     override fun onItemClick(product: Product) {
        val fragment = ProductDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("product", product)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
    fun addToCart(product: Product){

        val existingProduct = cart.find { it.id == product.id }
        if(existingProduct != null){
            existingProduct.quantity += product.quantity
        }else{
            cart.add(product)
        }

        Toast.makeText(this, "Product added to cart with ${product.quantity} items", Toast.LENGTH_SHORT).show()
    }

    fun openCartFragment(){
        val fragment = CartFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("cart",ArrayList(cart))
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
    fun getCart(): List<Product> {
    return cart
    }
}

