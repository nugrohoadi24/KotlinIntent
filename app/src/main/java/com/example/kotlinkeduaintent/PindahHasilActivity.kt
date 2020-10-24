package com.example.kotlinkeduaintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup

class PindahHasilActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnPilih : Button
    private lateinit var rgNomor : RadioGroup

    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 110
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pindah_hasil)

        btnPilih = findViewById(R.id.btn_pilih)
        rgNomor = findViewById(R.id.rg_nomor)
        
        btnPilih.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        //TODO("Not yet implemented")
        if(v.id == R.id.btn_pilih) {
            if(rgNomor.checkedRadioButtonId != 0) {
                var value = 0
                when (rgNomor.checkedRadioButtonId) {
                    R.id.rb_50 -> value = 50

                    R.id.rb_100 -> value = 100

                    R.id.rb_150 -> value = 150

                    R.id.rb_200 -> value = 200
                }

                /*
                Intent ini digunakan untuk mengirimkan kembali ke activity induk
                Perhatikan bahwa kita mencantumkan RESULT_CODE ke dalam metode setResult
                 */

                var resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value)
                setResult(RESULT_CODE, resultIntent)
                finish()
            }
        }
    }


}
