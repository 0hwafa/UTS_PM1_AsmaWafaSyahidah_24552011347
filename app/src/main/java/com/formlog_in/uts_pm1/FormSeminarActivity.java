package com.formlog_in.uts_pm1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FormSeminarActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone;
    private RadioGroup rgGender;
    private Spinner spSeminar;
    private CheckBox cbAgreement;
    private Button btnSubmit;
    private ImageView ivHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_seminar);

        // 1. Inisialisasi semua View sesuai ID di XML
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        rgGender = findViewById(R.id.rgGender);
        spSeminar = findViewById(R.id.spSeminar);
        cbAgreement = findViewById(R.id.cbAgreement);
        btnSubmit = findViewById(R.id.btnSubmit);
        ivHome = findViewById(R.id.ivHome);

        // 2. Logika Kembali ke Homepage (Icon Rumah di Header)
        if (ivHome != null) {
            ivHome.setOnClickListener(v -> {
                Intent intent = new Intent(FormSeminarActivity.this, HomeActivity.class);
                // Flag agar tidak menumpuk activity dan kembali ke Home yang sudah ada
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        // 3. Set Pilihan untuk Spinner
        String[] seminarOptions = {
                "Bio-Code: Bioinformatics & Genetics",
                "Automated Flora: Real-Time Monitoring",
                "Molecular Programming with DNA",
                "Synthetic Biology for Beginners",
                "Tech-Plant Seminar: Sustainable Future"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, seminarOptions);
        spSeminar.setAdapter(adapter);

        // 4. Klik Tombol Submit
        btnSubmit.setOnClickListener(v -> {
            if (validateInput()) {
                showConfirmationDialog();
            }
        });
    }

    // 5. Logika Validasi Input
    private boolean validateInput() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Nama tidak boleh kosong");
            return false;
        }

        if (email.isEmpty()) {
            etEmail.setError("Email tidak boleh kosong");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Format email tidak valid");
            return false;
        }

        if (phone.isEmpty()) {
            etPhone.setError("Nomor telepon tidak boleh kosong");
            return false;
        }
        // Validasi: Harus diawali 08 dan panjang 10-13 digit
        if (!phone.startsWith("08") || phone.length() < 10 || phone.length() > 13) {
            etPhone.setError("Nomor harus diawali 08 dan berjumlah 10-13 digit");
            return false;
        }

        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Pilih jenis kelamin terlebih dahulu", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!cbAgreement.isChecked()) {
            Toast.makeText(this, "Harap setujui syarat dan ketentuan", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // 6. Fungsi Dialog Konfirmasi & Kirim Data Lengkap
    private void showConfirmationDialog() {
        // Ambil data terbaru dari input
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String seminar = spSeminar.getSelectedItem().toString();

        // Ambil teks dari RadioButton yang dipilih (Male/Female)
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedGenderId);
        String gender = selectedRadioButton.getText().toString();

        new AlertDialog.Builder(this)
                .setTitle("Confirm Registration")
                .setMessage("Are you sure the data is correct?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Pindah ke SuccessActivity sambil membawa semua data
                    Intent intent = new Intent(FormSeminarActivity.this, SuccessActivity.class);
                    intent.putExtra("USER_NAME", name);
                    intent.putExtra("USER_EMAIL", email);
                    intent.putExtra("USER_PHONE", phone);
                    intent.putExtra("USER_GENDER", gender);
                    intent.putExtra("SEMINAR_NAME", seminar);

                    startActivity(intent);
                    finish(); // Tutup form agar tidak bisa kembali ke sini
                })
                .setNegativeButton("No", null)
                .show();
    }
}