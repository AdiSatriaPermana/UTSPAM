package lat.pam.kopi_dan_nyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val currentName = intent.getStringExtra("USER_NAME") ?: "User"

        val etProfileName = findViewById<EditText>(R.id.etProfileName)
        val btnSaveProfile = findViewById<Button>(R.id.btnSaveProfile)
        val btnBackToHome = findViewById<Button>(R.id.btnBackToHome)

        etProfileName.setText(currentName)

        btnSaveProfile.setOnClickListener {
            val newName = etProfileName.text.toString().trim()
            if (newName.isEmpty()) {
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ProfileActivity, HomeActivity::class.java)
                intent.putExtra("USER_NAME", newName)
                startActivity(intent)
                finishAffinity()
            }
        }

        btnBackToHome.setOnClickListener {
            finish()
        }
    }
}