package calcnumerico.ph.maquinabinaria.Activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import calcnumerico.ph.maquinabinaria.Helpers.Bissection
import calcnumerico.ph.mquinabinria.R
import kotlinx.android.synthetic.main.activity_bisection.*

class BisectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bisection)
        Log.i("Bissecção", Bissection.calculate("x^2+log(x)", 0.5, 1.0, 0.05, 100 ).toString())

    }
    @SuppressLint("SetTextI18n")
    fun calcRoot(view: View){
        txt_result.text = "Raiz: ${Bissection.calculate(et_function.text.toString(),
                et_range_a.text.toString().toDouble(),
                et_range_b.text.toString().toDouble(),
                et_tolerance.text.toString().toDouble(),
                et_repetition.text.toString().toInt()).toString()}"
    }
}
