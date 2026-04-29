package com.formlog_in.uts_pm1;

import android.content.Intent; // Tambahkan import ini
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi EditText Username untuk mengambil nama
        EditText etUsername = findViewById(R.id.etRegisterUsername);
        EditText etPass = findViewById(R.id.etRegisterPassword);
        EditText etConfirm = findViewById(R.id.etConfirmPassword);
        Button btnRegister = findViewById(R.id.btnCreateAccount);
        TextView tvLogin = findViewById(R.id.tvToLogin);

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String pass = etPass.getText().toString();
            String confirm = etConfirm.getText().toString();

            // Logika Validasi
            if (username.isEmpty()) {
                Toast.makeText(this, "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            } else if (pass.equals(confirm) && !pass.isEmpty()) {
                Toast.makeText(this, "Akun Berhasil Dibuat!", Toast.LENGTH_SHORT).show();

                // LOGIC UNTUK PINDAH KE HOME
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);

                // (Opsional) Kirim nama ke HomeActivity agar bisa tampil "Hello, User!"
                intent.putExtra("KEY_NAMA", username);

                startActivity(intent);

                // finish() dipanggil agar user tidak bisa balik ke halaman register
                // saat menekan tombol back dari Home
                finish();
            } else {
                Toast.makeText(this, "Password tidak cocok atau kosong!", Toast.LENGTH_SHORT).show();
            }
        });

        tvLogin.setOnClickListener(v -> finish());
    }
}