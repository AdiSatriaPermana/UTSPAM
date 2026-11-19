package lat.pam.kopi_dan_nyemil

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "menu_table")
data class Menu(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val imageResId: Int,
    val category: String
) {
    @Ignore
    var quantity: Int = 0
}