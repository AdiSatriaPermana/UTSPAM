package lat.pam.kopi_dan_nyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    // Inisialisasi ViewModel menggunakan activity-ktx
    private val viewModel: HomeViewModel by viewModels()
    private var currentDisplayedList: List<Menu> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userName = intent.getStringExtra("USER_NAME") ?: "User"

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

        // Setup Adapter dengan list awal kosong (akan diisi otomatis oleh LiveData Room)
        adapter = MenuAdapter(emptyList()) {
            viewModel.onCartUpdated(currentDisplayedList)
        }

        rvMenu.layoutManager = LinearLayoutManager(this)
        rvMenu.adapter = adapter

        // Observe semua menu dari Room untuk cache checkout
        viewModel.allMenusLiveData.observe(this) { allMenus ->
            viewModel.updateAllMenusCache(allMenus)
        }

        // Observe daftar menu yang sedang difilter dari Room
        viewModel.displayedMenus.observe(this) { menus ->
            currentDisplayedList = viewModel.syncQuantities(menus)
            adapter.updateData(currentDisplayedList)
        }

        // Observe kategori aktif agar tombol filter tetap ter-highlight meski layar di-rotate
        viewModel.activeCategory.observe(this) { category ->
            when (category) {
                "minuman" -> highlightFilter(btnFilterDrink, btnFilterAll, btnFilterFood)
                "makanan" -> highlightFilter(btnFilterFood, btnFilterAll, btnFilterDrink)
                else -> highlightFilter(btnFilterAll, btnFilterDrink, btnFilterFood)
            }
        }

        // Observe LiveData Total Item
        viewModel.totalItems.observe(this) { totalCount ->
            tvTotalItems.text = "$totalCount Item Dipilih"
        }

        // Observe LiveData Total Harga
        viewModel.totalPrice.observe(this) { totalPrice ->
            tvTotalPrice.text = "Total: Rp ${String.format("%,d", totalPrice).replace(',', '.')}"
        }

        // Logic Filter Kategori melalui ViewModel
        btnFilterAll.setOnClickListener {
            viewModel.setCategory("semua")
        }

        btnFilterDrink.setOnClickListener {
            viewModel.setCategory("minuman")
        }

        btnFilterFood.setOnClickListener {
            viewModel.setCategory("makanan")
        }

        // Tombol BAYAR
        btnCheckout.setOnClickListener {
            val selectedItems = viewModel.getSelectedItemsForCheckout()
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
    }

    private fun highlightFilter(active: Button, vararg inactive: Button) {
        active.setBackgroundResource(R.drawable.bg_neo_button)
        for (btn in inactive) {
            btn.setBackgroundResource(R.drawable.bg_neo_button_outline)
        }
    }
}