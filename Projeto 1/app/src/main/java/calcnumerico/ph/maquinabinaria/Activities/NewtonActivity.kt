package calcnumerico.ph.maquinabinaria.Activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import calcnumerico.ph.maquinabinaria.Helpers.Bissection
import calcnumerico.ph.maquinabinaria.Helpers.NearToZero
import calcnumerico.ph.maquinabinaria.Helpers.NewtonRaphson
import calcnumerico.ph.maquinabinaria.Helpers.StopCriteria
import calcnumerico.ph.mquinabinria.R
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_bisection.*
import kotlinx.android.synthetic.main.activity_newton.*

class NewtonActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newton)
    }
    @SuppressLint("SetTextI18n")
    fun calcRootNewton(view: View){
        try {
            val sc: StopCriteria = NearToZero(et_tolerance_n.text.toString().toDouble())
            val nr = NewtonRaphson(et_function_n.text.toString(),
                    et_first_approximation.text.toString().toDouble(),
                    et_repetition_n.text.toString().toInt(),
                    sc)
            txt_result_n.text = "Raiz: ${nr.run()}"
        }catch (e: NotImplementedError){
            txt_result_n.text = e.message
        }

    }
}
