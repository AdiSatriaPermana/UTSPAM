package lat.pam.kopi_dan_nyemil

data class Menu(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val imageResId: Int,
    val category: String,
    var quantity: Int = 0
)