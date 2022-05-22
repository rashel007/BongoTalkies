package com.rashel.myapplication.util

import java.io.InputStreamReader
import java.lang.Exception

class MockResponseFileReader(path: String) {
    val content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path))
        content = reader.readText()
        reader.close()

    }
}