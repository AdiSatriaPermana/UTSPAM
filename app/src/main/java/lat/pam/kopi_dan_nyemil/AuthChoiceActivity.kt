package lat.pam.kopi_dan_nyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AuthChoiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_choice)

        val btnGoRegister = findViewById<Button>(R.id.btnGoRegister)
        val btnGoLogin = findViewById<Button>(R.id.btnGoLogin)

        // Klik REGISTER -> Buka Layar Register
        btnGoRegister.setOnClickListener {
            val intent = Intent(this@AuthChoiceActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Klik LOGIN -> Buka Layar Login
        btnGoLogin.setOnClickListener {
            val intent = Intent(this@AuthChoiceActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}