package lat.pam.kopi_dan_nyemil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val menuDao = AppDatabase.getDatabase(application).menuDao()

    // Menyimpan kategori filter aktif ("semua", "minuman", "makanan")
    val activeCategory = MutableLiveData("semua")

    // Menyimpan kuantitas pesanan per ID menu agar aman saat rotasi layar / ganti filter
    private val cartQuantities = mutableMapOf<String, Int>()

    // LiveData ringkasan keranjang
    private val _totalItems = MutableLiveData(0)
    val totalItems: LiveData<Int> = _totalItems

    private val _totalPrice = MutableLiveData(0)
    val totalPrice: LiveData<Int> = _totalPrice

    // LiveData daftar menu dari Room yang otomatis berubah mengikuti activeCategory
    val displayedMenus: LiveData<List<Menu>> = activeCategory.switchMap { category ->
        if (category == "semua") {
            menuDao.getAllMenus()
        } else {
            menuDao.getMenusByCategory(category)
        }
    }

    init {
        seedInitialMenusIfEmpty()
    }

    private fun seedInitialMenusIfEmpty() {
        viewModelScope.launch(Dispatchers.IO) {
            if (menuDao.getMenuCount() == 0) {
                val initialMenus = listOf(
                    Menu("1", "Americano Cold Brew", "Seduhan kopi hitam dingin yang segar dan fruity.", 20000, R.drawable.img_americano, "minuman"),
                    Menu("2", "Caramel Macchiato", "Espresso mantap dipadu susu segar dan saus karamel.", 26000, R.drawable.img_caramel_macchiato, "minuman"),
                    Menu("3", "Choco Hazelnut", "Cokelat pekat melimpah dengan aroma gurih hazelnut.", 24000, R.drawable.img_choco_hazelnut, "minuman"),
                    Menu("4", "Churros Cinnamon", "Camilan renyah ditaburi gula kayu manis & saus cokelat.", 18000, R.drawable.img_churros, "makanan"),
                    Menu("5", "Waffle Ice Cream", "Waffle hangat renyah disajikan dengan scoop es krim vanila.", 23000, R.drawable.img_waffle, "makanan"),
                    Menu("6", "French Fries Bolognese", "Kentang goreng garing disiram saus daging bolognese & keju.", 21000, R.drawable.img_french_fries, "makanan")
                )
                menuDao.insertAll(initialMenus)
            }
        }
    }

    fun setCategory(category: String) {
        if (activeCategory.value != category) {
            activeCategory.value = category
        }
    }

    // Sinkronisasi kuantitas dari memory ViewModel ke list menu yang baru dimuat dari Room
    fun syncQuantities(menus: List<Menu>): List<Menu> {
        menus.forEach { menu ->
            menu.quantity = cartQuantities[menu.id] ?: 0
        }
        recalculateCart(menus)
        return menus
    }

    // Dipanggil setiap tombol + atau - di adapter ditekan
    fun onCartUpdated(currentVisibleMenus: List<Menu>) {
        currentVisibleMenus.forEach { menu ->
            if (menu.quantity > 0) {
                cartQuantities[menu.id] = menu.quantity
            } else {
                cartQuantities.remove(menu.id)
            }
        }
        recalculateCart(currentVisibleMenus)
    }

    private fun recalculateCart(visibleMenus: List<Menu>) {
        // Mengambil seluruh menu dari cache LiveData utama atau menghitung dari cartQuantities
        val allCached = allMenusCache
        if (allCached.isNotEmpty()) {
            allCached.forEach { it.quantity = cartQuantities[it.id] ?: 0 }
            _totalItems.value = allCached.sumOf { it.quantity }
            _totalPrice.value = allCached.sumOf { it.price * it.quantity }
        } else {
            _totalItems.value = visibleMenus.sumOf { it.quantity }
            _totalPrice.value = visibleMenus.sumOf { it.price * it.quantity }
        }
    }

    // Cache seluruh menu untuk keperluan checkout saat sedang memfilter kategori tertentu
    private var allMenusCache: List<Menu> = emptyList()

    val allMenusLiveData: LiveData<List<Menu>> = menuDao.getAllMenus()

    fun updateAllMenusCache(menus: List<Menu>) {
        allMenusCache = menus
        recalculateCart(menus)
    }

    fun getSelectedItemsForCheckout(): List<Menu> {
        return allMenusCache.map { menu ->
            menu.apply { quantity = cartQuantities[menu.id] ?: 0 }
        }.filter { it.quantity > 0 }
    }
}