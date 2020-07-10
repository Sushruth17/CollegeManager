package com.example.login_and_signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_particular_year.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopperParticularYear.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopperParticularYear : Fragment(), AdapterView.OnItemSelectedListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        year_spinner.visibility = View.VISIBLE
        var view = inflater.inflate(R.layout.fragment_particular_year, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        year_spinner.visibility = View.VISIBLE
        val spinnerYear: Spinner? = getView()?.findViewById(R.id.year_spinner)
        val arrayYear = R.array.year_array
        createSpinner(spinnerYear,arrayYear)

        val spinnerBranch: Spinner? = getView()?.findViewById(R.id.branch_spinner)
        val arrayBranch = R.array.branch_array
        createSpinner(spinnerBranch,arrayBranch)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ParticularYear.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TopperParticularYear().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            if(parent.id == R.id.year_spinner)
            {
                val selectedYear: Int = year_spinner.selectedItem.toString().toInt()
                Log.i("spinner","---selected year---"+selectedYear)
                val classRepo = Repo()
                val rv_topper_list_id = getView()?.findViewById<RecyclerView>(R.id.rv_py_topper_list)
                if (rv_topper_list_id != null) {
                    classRepo.getTopper(selectedYear,rv_topper_list_id,context )
                }
            }
/*            else if(parent.id == R.id.branch_spinner)
            {
                val selectedYear: Int = year_spinner.selectedItem.toString().toInt()
                Log.i("spinner","---selected year---"+selectedYear)
                val selectedBranch: String = branch_spinner.selectedItem.toString()
                Log.i("spinner","---selected BRANCH---"+selectedBranch)
            }*/
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    fun createSpinner(spinner : Spinner?, array : Int){
        if (spinner != null) {
            spinner.onItemSelectedListener = this
        }

        activity!!.applicationContext.let {
            ArrayAdapter.createFromResource(
                it,
                array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                if (spinner != null) {
                    spinner.adapter = adapter
                }

            }
        }
    }
}
