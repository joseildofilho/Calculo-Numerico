package calcnumerico.ph.maquinabinaria.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import calcnumerico.ph.mquinabinria.R
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_simulator.*
import com.jjoe64.graphview.series.LineGraphSeries
import calcnumerico.ph.mquinabinria.R.id.graph





class SimulatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulator)
        val points = arrayOfNulls<DataPoint>(100)
        for (i in points.indices) {
            points[i] = DataPoint(i.toDouble(), Math.sin(i * 0.5) * 20.0 * (Math.random() * 10 + 1))
        }
        val series = LineGraphSeries<DataPoint>(points)

        // set manual X bounds
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.setMinY(-150.0)
        graph.viewport.setMaxY(150.0)

        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(4.0)
        graph.viewport.setMaxX(80.0)

        // enable scaling and scrolling
        graph.viewport.isScalable = true
        graph.viewport.setScalableY(true)

        graph.addSeries(series)
    }
}
