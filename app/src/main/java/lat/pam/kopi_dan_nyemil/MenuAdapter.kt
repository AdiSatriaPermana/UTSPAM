package lat.pam.kopi_dan_nyemil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(
    private var menuList: List<Menu>,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgMenu: ImageView = itemView.findViewById(R.id.imgMenu)
        val tvName: TextView = itemView.findViewById(R.id.tvMenuName)
        val tvDesc: TextView = itemView.findViewById(R.id.tvMenuDesc)
        val tvPrice: TextView = itemView.findViewById(R.id.tvMenuPrice)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
        val btnMinus: Button = itemView.findViewById(R.id.btnMinus)
        val btnPlus: Button = itemView.findViewById(R.id.btnPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        holder.imgMenu.setImageResource(menu.imageResId)
        holder.tvName.text = menu.name
        holder.tvDesc.text = menu.description
        holder.tvPrice.text = "Rp ${String.format("%,d", menu.price).replace(',', '.')}"
        holder.tvQuantity.text = menu.quantity.toString()

        // Klik Plus (+)
        holder.btnPlus.setOnClickListener {
            menu.quantity++
            holder.tvQuantity.text = menu.quantity.toString()
            onQuantityChanged()
        }

        // Klik Minus (-)
        holder.btnMinus.setOnClickListener {
            if (menu.quantity > 0) {
                menu.quantity--
                holder.tvQuantity.text = menu.quantity.toString()
                onQuantityChanged()
            }
        }
    }

    override fun getItemCount(): Int = menuList.size

    // Fungsi untuk memperbarui filter kategori
    fun updateData(newList: List<Menu>) {
        menuList = newList
        notifyDataSetChanged()
    }
}