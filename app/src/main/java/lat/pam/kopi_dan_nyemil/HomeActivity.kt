package lat.pam.kopi_dan_nyemil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private var userName: String = "User"
    private lateinit var adapter: MenuAdapter
    private lateinit var tvTotalItems: TextView
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnFilterAll: Button
    private lateinit var btnFilterDrink: Button
    private lateinit var btnFilterFood: Button

    // Master List 6 Menu
    private val allMenuList = listOf(
        Menu("1", "Americano Cold Brew", "Seduhan kopi hitam dingin yang segar dan fruity.", 20000, R.drawable.img_americano, "minuman"),
        Menu("2", "Caramel Macchiato", "Espresso mantap dipadu susu segar dan saus karamel.", 26000, R.drawable.img_caramel_macchiato, "minuman"),
        Menu("3", "Choco Hazelnut", "Cokelat pekat melimpah dengan aroma gurih hazelnut.", 24000, R.drawable.img_choco_hazelnut, "minuman"),
        Menu("4", "Churros Cinnamon", "Camilan renyah ditaburi gula kayu manis & saus cokelat.", 18000, R.drawable.img_churros, "makanan"),
        Menu("5", "Waffle Ice Cream", "Waffle hangat renyah disajikan dengan scoop es krim vanila.", 23000, R.drawable.img_waffle, "makanan"),
        Menu("6", "French Fries Bolognese", "Kentang goreng garing disiram saus daging bolognese & keju.", 21000, R.drawable.img_french_fries, "makanan")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userName = intent.getStringExtra("USER_NAME") ?: "User"

//        val rootLayout = findViewById<View>(R.id.rvMenu).parent as View
//        ViewCompat.setOnApplyWindowInsetsListener(rootLayout) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(v.paddingLeft, systemBars.top + 16, v.paddingRight, systemBars.bottom + 16)
//            insets
//        }

        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val rvMenu = findViewById<RecyclerView>(R.id.rvMenu)
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)

        tvTotalItems = findViewById(R.id.tvTotalItems)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)

        btnFilterAll = findViewById(R.id.btnFilterAll)
        btnFilterDrink = findViewById(R.id.btnFilterDrink)
        btnFilterFood = findViewById(R.id.btnFilterFood)

        tvGreeting.text = "Halo, $userName!"

        btnProfile.setOnClickListener {
            val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
        }

        // Setup Adapter
        adapter = MenuAdapter(allMenuList) {
            updateCartSummary()
        }

        rvMenu.layoutManager = LinearLayoutManager(this)
        rvMenu.adapter = adapter

        // Logic Filter Kategori
        btnFilterAll.setOnClickListener {
            adapter.updateData(allMenuList)
            highlightFilter(btnFilterAll, btnFilterDrink, btnFilterFood)
        }

        btnFilterDrink.setOnClickListener {
            adapter.updateData(allMenuList.filter { it.category == "minuman" })
            highlightFilter(btnFilterDrink, btnFilterAll, btnFilterFood)
        }

        btnFilterFood.setOnClickListener {
            adapter.updateData(allMenuList.filter { it.category == "makanan" })
            highlightFilter(btnFilterFood, btnFilterAll, btnFilterDrink)
        }

        // Tombol BAYAR
        btnCheckout.setOnClickListener {
            val selectedItems = allMenuList.filter { it.quantity > 0 }
            if (selectedItems.isEmpty()) {
                Toast.makeText(this, "Pilih minimal 1 menu untuk dipesan!", Toast.LENGTH_SHORT).show()
            } else {
                val orderSummary = selectedItems.joinToString("\n") { "${it.name} (${it.quantity}x)" }
                val totalPrice = selectedItems.sumOf { it.price * it.quantity }

                val intent = Intent(this@HomeActivity, OrderAddressActivity::class.java)
                intent.putExtra("USER_NAME", userName)
                intent.putExtra("MENU_NAME", orderSummary)
                intent.putExtra("MENU_PRICE", totalPrice)
                startActivity(intent)
            }
        }

        updateCartSummary()
    }

    private fun updateCartSummary() {
        val totalCount = allMenuList.sumOf { it.quantity }
        val totalPrice = allMenuList.sumOf { it.price * it.quantity }

        tvTotalItems.text = "$totalCount Item Dipilih"
        tvTotalPrice.text = "Total: Rp ${String.format("%,d", totalPrice).replace(',', '.')}"
    }

    private fun highlightFilter(active: Button, vararg inactive: Button) {
        active.setBackgroundResource(R.drawable.bg_neo_button)
        for (btn in inactive) {
            btn.setBackgroundResource(R.drawable.bg_neo_button_outline)
        }
    }
}