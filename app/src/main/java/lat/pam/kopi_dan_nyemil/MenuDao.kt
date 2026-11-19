package lat.pam.kopi_dan_nyemil

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDao {

    @Query("SELECT * FROM menu_table ORDER BY id ASC")
    fun getAllMenus(): LiveData<List<Menu>>

    @Query("SELECT * FROM menu_table WHERE category = :category ORDER BY id ASC")
    fun getMenusByCategory(category: String): LiveData<List<Menu>>

    @Query("SELECT COUNT(*) FROM menu_table")
    suspend fun getMenuCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(menus: List<Menu>)
}