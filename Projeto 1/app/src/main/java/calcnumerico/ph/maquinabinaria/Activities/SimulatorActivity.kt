package calcnumerico.ph.maquinabinaria.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import calcnumerico.ph.maquinabinaria.Helpers.Simulator
import calcnumerico.ph.mquinabinria.R
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_simulator.*
import com.jjoe64.graphview.series.LineGraphSeries
import calcnumerico.ph.mquinabinria.R.id.graph
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import com.jjoe64.graphview.series.PointsGraphSeries


class SimulatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulator)


        bt_simulator.setOnClickListener(View.OnClickListener {
            graphics(et_b.text.toString().toInt(), et_t.text.toString().toInt(), et_l.text.toString().toInt(),
                    et_u.text.toString().toInt())
        })

    }
    fun graphics(b: Int, t: Int, l: Int, u: Int){

        val s = Simulator(b, t, l, u)
        //Log.i("Simulator", s.get(0).toString())
        val values = s.allValuesDouble
        val points = arrayOfNulls<DataPoint>(values.size)
        for (i in points.indices) {
            points[i] = DataPoint(values[i], 1.0)
        }

        val series = PointsGraphSeries<DataPoint>(points)

        // set manual X bounds
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.setMinY(-10.0)
        graph.viewport.setMaxY(10.0)

        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(-150.0)
        graph.viewport.setMaxX(150.0)

        // enable scaling and scrolling
        graph.viewport.isScalable = true
        graph.viewport.setScalableY(false)

        graph.viewport.isScrollable = true
        graph.viewport.setScrollableY(true)

        graph.addSeries(series)
    }
}
