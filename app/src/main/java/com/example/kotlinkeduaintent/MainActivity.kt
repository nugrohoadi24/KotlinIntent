package com.example.kotlinkeduaintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.kotlinkeduaintent.PindahObjectActivity.Companion.EXTRA_PERSON

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvHasil: TextView

    companion object {
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPindahActivity: Button = findViewById(R.id.btn_pindah)
        btnPindahActivity.setOnClickListener(this)

        val btnPindahDataActivity: Button = findViewById(R.id.btn_pindahData)
        btnPindahDataActivity.setOnClickListener(this)

        val btnNomorHandphone: Button = findViewById(R.id.btn_nomorHP)
        btnNomorHandphone.setOnClickListener(this)

        val btnPindahObjectActivity: Button = findViewById(R.id.btn_object)
        btnPindahObjectActivity.setOnClickListener(this)

        val btnPindahHasilActivity: Button = findViewById(R.id.btn_result)
        btnPindahHasilActivity.setOnClickListener(this)

        tvHasil = findViewById(R.id.tv_result)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_pindah -> {
                /*
                Intent untuk memulai activity baru
                */
                val moveIntent = Intent(this@MainActivity, PindahActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_pindahData -> {
                /*
                Intent untuk mengirimkan data ke activity lain
                 */
                val moveWithDataIntent = Intent(this@MainActivity, PindahDataActivity::class.java)
                moveWithDataIntent.putExtra(PindahDataActivity.EXTRA_NAME, "Nugroho Adi Pratomo")
                moveWithDataIntent.putExtra(PindahDataActivity.EXTRA_AGE, 22)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_nomorHP -> {
                /*
                Intent action untuk menjalankan action dial
                 */
                val nomorHandphone = "087888760662"
                val moveDialNumber = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $nomorHandphone"))
                startActivity(moveDialNumber)
            }

            R.id.btn_object -> {
                /*
                Intent untuk mengirimkan object ke activity lain, perlu diingat bahwa object Person adalah parcelable
                 */
                val person = Person(
                    "Nugroho Adi Pratomo",
                    22,
                    "nugrohoadi.pratomo24@gmail.com",
                    "Bekasi"
                )

                val moveWithObjectActivity =
                    Intent(this@MainActivity, PindahObjectActivity::class.java)
                moveWithObjectActivity.putExtra(PindahObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectActivity)
            }

            R.id.btn_result -> {
                /*
                Intent for result bermanfaat ketika kita ingin mendapatkan nilai balikan dari activity lainnya
                Perhatikan bahwa kita mengirimkan intent beserta REQUEST_CODE
                 */
                val moveWithResultActivity =
                    Intent(this@MainActivity, PindahHasilActivity::class.java)
                startActivityForResult(moveWithResultActivity, REQUEST_CODE)
            }
        }
    }

    /*
    Callback onActvityResult dipanggil setelah kode finish() di dalam MoveForResultActivity dipanggil
    Perhatikan pengecekan request code yang diterima
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /*
        Callback onActvityResult dipanggil setelah kode finish() di dalam MoveForResultActivity dipanggil
        Perhatikan pengecekan request code yang diterima
        */
        if (requestCode == REQUEST_CODE) {
            if (resultCode == PindahHasilActivity.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(PindahHasilActivity.EXTRA_SELECTED_VALUE, 0)
                tvHasil.text = "Hasil : $selectedValue"
            }
        }

    }
}