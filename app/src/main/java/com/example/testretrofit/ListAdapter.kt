package com.example.testretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import androidx.core.os.bundleOf


class ListAdapter(val context: title, val list: ArrayList<MyData>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val context = parent?.getContext()

        val view:View=LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false)
        val login = view.findViewById<TextView>(R.id.plogin)
        val avatar_url = view.findViewById<ImageView>(R.id.userImage)
        val followBT = view.findViewById<Button>(R.id.bt_follow)
        val userLayout = view.findViewById<LinearLayout>(R.id.userLayout)

        login.text=list[position].login
        Picasso.get().load(list[position].avatar_url).into(avatar_url)

        followBT.setOnClickListener(){
            if (followBT.text == "Follow"){
                followBT.setText("Unfollow")
                followBT.setBackgroundResource(R.color.unfollowBT)
            }else{
                followBT.setText("Follow")
                followBT.setBackgroundResource(R.color.followBt)
            }
        }
        userLayout.setOnClickListener(){
            var userBundle = bundleOf("user_name_bundle" to list[position].login.toString())
            view.findNavController().navigate(R.id.action_title2_to_profile,userBundle)
        }
        return view
    }
    override fun getItem(position: Int): Any {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return position
    }
    override fun getItemId(position: Int): Long {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return position.toLong()

    }

    override fun getCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return list.size
    }



}