package lat.pam.kopi_dan_nyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        val userName = intent.getStringExtra("USER_NAME") ?: "Pelanggan"
        val menuDetails = intent.getStringExtra("MENU_NAME") ?: "Tidak ada detail pesanan"

        val tvCustomerName = findViewById<TextView>(R.id.tvCustomerName)
        val tvOrderDetails = findViewById<TextView>(R.id.tvOrderDetails)
        val btnDone = findViewById<Button>(R.id.btnDone)

        tvCustomerName.text = userName
        tvOrderDetails.text = menuDetails

        btnDone.setOnClickListener {
            val intent = Intent(this@OrderConfirmationActivity, HomeActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
            finishAffinity()
        }
    }
}