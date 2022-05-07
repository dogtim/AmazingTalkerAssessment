package com.amazingtalker.assessment.apis

import android.content.Context
import com.amazingtalker.assessment.data.DateUtility
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class FetchTutorTime(context: Context, name: String) {
    private val TAG = FetchTutorTime::class.qualifiedName
    private val url: String = "https://en.amazingtalker.com/v1/guest/teachers/" + name + "/schedule?started_at=" + DateUtility.getSpecial()
    private lateinit var stringRequest: StringRequest // Assume this exists.
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun fetch(
        listener: Response.Listener<String> ,
        errorListener: Response.ErrorListener? = null) {
        stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
        requestQueue.add(stringRequest)
    }

    fun cancel() {
        requestQueue.cancelAll(TAG)
    }
}