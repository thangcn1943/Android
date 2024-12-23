package com.example.studentmannavigation

data class StudentModel(val studentName: String, val studentId: String) {
    override fun toString(): String {
        return "$studentName\n$studentId"
    }
}
