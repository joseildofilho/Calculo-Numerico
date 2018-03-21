package calcnumerico.ph.maquinabinaria.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import calcnumerico.ph.maquinabinaria.Helpers.BinConverter
import calcnumerico.ph.mquinabinria.R
import kotlinx.android.synthetic.main.activity_binary_machine.*

class BinaryMachineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binary_machine)
    }
    fun convert(view: View) {
        val valorBinario = BinConverter.converter(et_val.text.toString().toDouble())
        text_result.text = valorBinario
    }
}
