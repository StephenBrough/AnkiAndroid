package com.sbrough.ankiandroid.util

import android.content.Context
import android.net.Uri
import java.io.*
import java.util.zip.ZipFile


/**
 * Downloads a file from the uri given.
 */
fun Uri.downloadFile(file: File? = null, isSaveToInternal: Boolean = true) {


}

fun InputStream.copyTo(file: File) = use { input -> file.outputStream().use { output -> input.copyTo(output) } }

// Downloads a file using the current FileInputStream
fun FileInputStream.downloadFile(context: Context, filename: String) {
    val file = context.getDir(filename, Context.MODE_PRIVATE)
}

fun File.unzip(destination: File) {
    val zipFile = ZipFile(this)
    val collectionEntry = zipFile.getEntry("collection.anki2")
    val bufferedZipInput = BufferedInputStream(zipFile.getInputStream(collectionEntry))

    val output = FileOutputStream(destination)

    var dataRead = bufferedZipInput.read()
    while(dataRead != -1) {
        output.write(dataRead)
        dataRead = bufferedZipInput.read()
    }
 }