package com.ppb.reminderplus.ui.Insert.newitem

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.RadioGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ppb.reminderplus.AssigmentApplication
import com.ppb.reminderplus.AssigmentViewModel
import com.ppb.reminderplus.AssigmentViewModelFactory
import com.ppb.reminderplus.R
import com.ppb.reminderplus.database.Assigment
import com.ppb.reminderplus.database.AssigmentWithCourse
import com.ppb.reminderplus.database.Course
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NewAssigment : Fragment() {
    private val assigmentViewModel: AssigmentViewModel by viewModels {
        AssigmentViewModelFactory((activity?.application as AssigmentApplication).repository)
    }
    lateinit var calendar: Calendar
    var year: Int = 0
    var month: Int = 0
    var dayOfMonth: Int = 0

    var hour: Int = 0
    var minute: Int = 0
    var is24HourFormat: Boolean = true
    var listCourse = emptyList<AssigmentWithCourse>()

    var course: String = ""
    var priority: String = ""
    var dateAssigment: String = ""
    var positionCourse: Int = 0

    lateinit var datePickerDialog: DatePickerDialog
    lateinit var timePickerDialog: TimePickerDialog

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_new_assigment, container, false)
        val selectDate: Button = root.findViewById(R.id.btnDate)
        val selectTime: Button = root.findViewById(R.id.btnTime)
        val selectPriority: RadioGroup = root.findViewById(R.id.radioGroup1)
        val selectCourse: Spinner = root.findViewById(R.id.spinnerCourse)
        val buttonCreate: Button = root.findViewById(R.id.buttonCreate)
        val title: EditText = root.findViewById(R.id.newTitleAssigment)
        val description: EditText = root.findViewById(R.id.newDescriptionAssigment)
        val buttonCancel: Button = root.findViewById(R.id.buttonCancel)


        assigmentViewModel.assigmentsAndCourse.observe(viewLifecycleOwner) { result ->
            result.let {
                val arrayCourseName:ArrayList<String> = ArrayList()
                val arrayCourseId:ArrayList<Int> = ArrayList()
                this.listCourse = it.toList()
                val itr = listCourse.listIterator()
                while (itr.hasNext()) {
                    val c: Course = itr.next().course
                    val tmp: String = c.name
                    val tmp2: Int = c.course_id
                    arrayCourseName.add(tmp)
                    arrayCourseId.add(tmp2)
                }
                val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayCourseName)
                selectCourse.adapter = arrayAdapter
            }
        }

        selectCourse.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                positionCourse = 1
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                course = parent?.getItemAtPosition(position).toString()
                positionCourse = position + 1
            }

        }


        selectPriority.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioLow -> priority = "LOW"
                R.id.radioNormal -> priority = "Normal"
                R.id.radioHigh -> priority = "High"
                else -> {
                }
            }
        })

        selectDate.setOnClickListener(View.OnClickListener {
            calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            datePickerDialog = DatePickerDialog(
                    requireActivity(),
                    { _, year, month, day ->
                        selectDate.setText(day.toString() + "/" + (month + 1) + "/" + year)
                        dateAssigment = day.toString() + "/" + (month + 1) + "/" + year
                    },
                    year,
                    month,
                    dayOfMonth
            )
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis())
            datePickerDialog.show()
            selectDate.setBackgroundColor(getColor(activity?.applicationContext!!,R.color.colorPrimary))
            selectDate.setTextColor(Color.WHITE)
        })

        selectTime.setOnClickListener({
            calendar = Calendar.getInstance()
            hour = calendar.get(Calendar.HOUR_OF_DAY)
            minute = calendar.get(Calendar.MINUTE)
            timePickerDialog = TimePickerDialog(
                    activity,
                    3,
                    { _, hour, minute ->
                        selectTime.setText(hour.toString() + ":" + minute.toString())
                        dateAssigment += " " + hour.toString() + ":" + minute.toString()
                    },
                    hour, minute, DateFormat.is24HourFormat(activity)
            )
            timePickerDialog.show()
            selectTime.setBackgroundColor(getColor(activity?.applicationContext!!,R.color.colorPrimary))
            selectTime.setTextColor(Color.WHITE)
        })

        buttonCreate.setOnClickListener {
            try {
                val cal: Calendar = Calendar.getInstance()
                val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date: Date? = formatter.parse(dateAssigment)
                cal.setTimeInMillis(date?.time!!)
                var assigment = Assigment(0, title.text.toString(), description.text.toString(), priority, cal, "null", "Belum selesai", positionCourse)
                assigmentViewModel.insertAssigment(assigment)

                activity?.supportFragmentManager?.popBackStack()
            }catch(e: Exception){
                Toast.makeText(activity,"Set Tanggal & Waktu",Toast.LENGTH_SHORT).show()
            }
        }

        buttonCancel.setOnClickListener(View.OnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        })

        return root
    }


}