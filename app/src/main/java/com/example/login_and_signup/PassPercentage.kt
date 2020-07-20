package com.example.login_and_signup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_and_signup.model.MarksItem
import com.example.login_and_signup.utils.ApiStudent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


class PassPercentage : AppCompatActivity() {
    lateinit var context: Context
    var passPercentage: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_percentage)

/*        val pp1 :Int = PassPercentage(20172018)*/
        PassPercentage(20182019)
        val pp2 = 25
//        Log.i("pp2", "pp2 %" + pp2)

/*        val pp3 :Int = PassPercentage(20192020)*/
/*        val pp4 :Int = PassPercentage(20202021)*/


        val graph = findViewById<GraphView>(R.id.graph)
        val series: LineGraphSeries<DataPoint> = LineGraphSeries(
            arrayOf(
                DP(0, 95),
                DP(1, pp2),
                DP(2, 64),
                DP(3, 88)
            )
        )
        graph.addSeries(series)
//        graph.viewport.setMinX(0.0)
        graph.viewport.setMinY(0.0)
        graph.viewport.setMaxY(100.0)
        graph.viewport.isYAxisBoundsManual = true
//        graph.viewport.isXAxisBoundsManual = true


        val staticLabelsFormatter = StaticLabelsFormatter(graph)
        staticLabelsFormatter.setHorizontalLabels(
            arrayOf(
                "2017-18",
                "2018-19",
                "2019-20",
                "2020-21"
            )
        )
        graph.gridLabelRenderer.labelFormatter = staticLabelsFormatter
    }


    fun DP(a: Int, b: Int): DataPoint {
        return DataPoint(a.toDouble(), b.toDouble())
    }

    fun PassPercentage(year: Int) {
        ApiStudent()
            .addRetroFit()
            ?.getPassPercentage(year)
            ?.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val msg = response.body()?.string()
                        Log.i("api", "---TTTT :: GET msg from server :: " + msg)
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

                    }


                }
            })
    }
}
