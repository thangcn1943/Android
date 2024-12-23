package com.example.studentmannavigation

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<StudentModel>
    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

    private val addStudentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val hoten = result.data?.getStringExtra("hoten")
            val mssv = result.data?.getStringExtra("mssv")
            if (!hoten.isNullOrEmpty() && !mssv.isNullOrEmpty()) {
                students.add(StudentModel(hoten, mssv))
                adapter.notifyDataSetChanged() // Cập nhật giao diện ListView
            }
        }
    }

    private val editStudentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val pos = result.data?.getIntExtra("pos", -1) ?: -1
            val hoten = result.data?.getStringExtra("hoten")
            val mssv = result.data?.getStringExtra("mssv")

            if (pos != -1 && !hoten.isNullOrEmpty() && !mssv.isNullOrEmpty()) {
                students[pos] = StudentModel(hoten, mssv)
                adapter.notifyDataSetChanged() // Cập nhật ListView
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Thiết lập Toolbar làm Action Bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)  // Chỉ ra Toolbar làm Action Bar chính

        // Các phần còn lại của mã (ListView, adapter, v.v.)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        registerForContextMenu(listView)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_student -> {
                // Mở activity để thêm sinh viên
                val intent = Intent(this, AddStudentActivity::class.java)
                addStudentLauncher.launch(intent)
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings selected", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val pos = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        when (item.itemId) {
            R.id.action_edit_student -> {
                val intent = Intent(this, EditStudentActivity::class.java).apply {
                    putExtra("pos", pos)
                    putExtra("hoten", students[pos].studentName)
                    putExtra("mssv", students[pos].studentId)
                }
                editStudentLauncher.launch(intent)
            }
            R.id.action_delete -> {
                students.removeAt(pos)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Removed student", Toast.LENGTH_LONG).show()
            }
        }
        return super.onContextItemSelected(item)
    }

}



