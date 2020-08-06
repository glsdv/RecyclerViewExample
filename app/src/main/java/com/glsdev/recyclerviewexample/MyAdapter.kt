package com.glsdev.recyclerviewexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter (
    context: Context?,
    data: List<String>
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val mData: List<String> = data
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null


    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view: View = mInflater.inflate(R.layout.row, parent, false)
        return MyViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val person = mData[position]
        holder.myTextView.text = person
    }


    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val myTextView: TextView = itemView.findViewById<TextView>(R.id.title_text)

        override fun onClick(view: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String {
        return mData[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: MainActivity) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }




}