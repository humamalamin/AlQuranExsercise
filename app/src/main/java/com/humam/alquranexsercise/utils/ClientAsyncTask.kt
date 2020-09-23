package com.humam.alquranexsercise.utils

import android.os.AsyncTask
import com.humam.alquranexsercise.fragment.FragmentJAdwalSolat
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ClientAsyncTask (private val mContext: FragmentJAdwalSolat, postExecuteListener: OnPostExecuteListener) : AsyncTask<String, String, String>() {
    val CONNECTION_TIMEOUT_MILLISECONDS = 6000
    private val mPostExecuteListener: OnPostExecuteListener = postExecuteListener

    interface OnPostExecuteListener {
        fun onPostExecute(result: String)
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        mPostExecuteListener.onPostExecute(result)
    }

    override fun doInBackground(vararg params: String?): String {
        var urlConnection: HttpURLConnection? = null

        try {
            val url = URL(params[0])

            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = CONNECTION_TIMEOUT_MILLISECONDS
            urlConnection.readTimeout = CONNECTION_TIMEOUT_MILLISECONDS

            val inString = streamToString(urlConnection.inputStream)

            return inString
        } catch (ex: Exception) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect()
            }
        }

        return ""
    }

    fun streamToString(inputStream: InputStream) : String {
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var result = ""

        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    result += line
                }
            } while (true)
            inputStream.close()
        } catch (ex: Exception) {

        }

        return result
    }
}