package com.example.testretrofit


import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_title.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL



/**
 * A simple [Fragment] subclass.
 */
class title : Fragment() {

    val list=ArrayList<MyData>()
    val adapter=ListAdapter(this,list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_title, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url ="https://api.github.com/users"
        AsyncTaskHandler().execute(url)
    }

    inner class AsyncTaskHandler: AsyncTask<String, String, String>(){

        override fun doInBackground(vararg url: String?): String {
            val res:String
            val connection= URL(url[0]).openConnection()as HttpURLConnection
            try{
                connection.connect()
                res=connection.inputStream.use { it.reader().use { reader->reader.readText() } }
            }finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            jsonResult(result)
        }
        private fun jsonResult(jsonString:String?){
            val jsonArray= JSONArray(jsonString)
            var i=0
            while (i<jsonArray.length()){
                val jsonObject=jsonArray.getJSONObject(i)
                list.add(
                    MyData(
                        jsonObject.getString("login"),
                        jsonObject.getString("avatar_url")
                    )
                )
                i++
            }

            mylist.adapter=adapter
        }
    }
}

