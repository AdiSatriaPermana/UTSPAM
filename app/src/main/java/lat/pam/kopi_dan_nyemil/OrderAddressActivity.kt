package lat.pam.kopi_dan_nyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_address)

        val userName = intent.getStringExtra("USER_NAME") ?: "User"
        val menuName = intent.getStringExtra("MENU_NAME") ?: "Menu Kopi & Nyemil"
        val menuPrice = intent.getIntExtra("MENU_PRICE", 0)

        val tvOrderSummary = findViewById<TextView>(R.id.tvOrderSummary)
        val tvOrderPrice = findViewById<TextView>(R.id.tvOrderPrice)
        val etRecipientName = findViewById<EditText>(R.id.etRecipientName)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etNotes = findViewById<EditText>(R.id.etNotes)
        val btnSubmitOrder = findViewById<Button>(R.id.btnSubmitOrder)

        tvOrderSummary.text = "Pesanan: $menuName"
        tvOrderPrice.text = "Total: Rp ${String.format("%,d", menuPrice).replace(',', '.')}"
        etRecipientName.setText(userName)

        btnSubmitOrder.setOnClickListener {
            val recipient = etRecipientName.text.toString().trim()
            val address = etAddress.text.toString().trim()

            if (recipient.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Nama dan Alamat pengiriman wajib diisi!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@OrderAddressActivity, OrderConfirmationActivity::class.java)
                intent.putExtra("USER_NAME", recipient)
                intent.putExtra("MENU_NAME", menuName)
                startActivity(intent)
                finish()
            }
        }
    }
}