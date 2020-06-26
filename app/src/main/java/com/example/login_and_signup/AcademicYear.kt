package com.example.login_and_signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_topper_list.*
import kotlinx.android.synthetic.main.fragment_academic_year.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AcademicYear.newInstance] factory method to
 * create an instance of this fragment.
 */
class AcademicYear : Fragment() {
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
        return inflater.inflate(R.layout.fragment_academic_year, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        val spinner: Spinner? = getView()?.findViewById(R.id.topper_spinner)
        if (spinner != null) {
            spinner.onItemSelectedListener = this
        }


        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.topper_array,
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

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        when (pos) {
            0 -> param1?.let { param2?.let { it1 -> AcademicYear.newInstance(it, it1) } }?.let {
                getFragmentManager()?.beginTransaction()
                    ?.replace(R.id.container, it)?.commit()
            }
            1 -> param1?.let { param2?.let { it1 -> AnyYear.newInstance(it, it1) } }?.let {
                getFragmentManager()?.beginTransaction()
                    ?.replace(R.id.container, it)?.commit()
            }
            else -> param1?.let { param2?.let { it1 -> ParticularYear.newInstance(it, it1) } }?.let {
                getFragmentManager()?.beginTransaction()
                    ?.replace(R.id.container, it)?.commit()
            }
        }
       *//* if (pos == 0)
            showToast(message = "Spinner 1 Position:${pos}")
        else if (pos == 1)
            showToast(message = "Spinner 1 Position:${pos}")
        else if (pos == 2) {
            showToast(message = "Spinner 1 Position:${pos}")
            year_spinner.visibility = View.VISIBLE
            val yearSpinner: Spinner? = getView()?.findViewById(R.id.year_spinner)
            if (yearSpinner != null) {
                yearSpinner.onItemSelectedListener = this
            }
            activity?.let {
                ArrayAdapter.createFromResource(
                    it,
                    R.array.year_array,
                    android.R.layout.simple_spinner_item
                ).also { yearAdapter ->
                    yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    if (yearSpinner != null) {
                        yearSpinner.adapter = yearAdapter
                    }
                }
            }
        }*//*
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
        showToast(message = "Nothing selected")
    }

    private fun showToast(
        context: Context? = getActivity()?.getApplicationContext(),
        message: String,
        duration: Int = Toast.LENGTH_LONG
    ) {
        Toast.makeText(context, message, duration).show()*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AcademicYear.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AcademicYear().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}