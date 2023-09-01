package com.example.market

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyDetailAdapter(val detail: ArrayList<MyDetailItem>): BaseAdapter() {
    override fun getCount(): Int {
        return detail.size
    }

    override fun getItem(position: Int): Any {
        return detail[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (convertView == null) convertView = LayoutInflater.from(parent?.context).inflate(R.layout.activity_detail, parent, false)

        val item : MyDetailItem = detail[position]

        val image = convertView?.findViewById<View>(R.id.detail_iv_item) as ImageView
        val profile = convertView.findViewById<View>(R.id.iv_profile) as TextView
        val nickname = convertView.findViewById<View>(R.id.tv_nickname) as TextView
        val addr = convertView.findViewById<View>(R.id.tv_deteil_address) as TextView
        val detailTitle = convertView.findViewById<View>(R.id.tv_item_title) as TextView
        val detailInfo = convertView.findViewById<View>(R.id.tv_tiem_info) as TextView
        val detailPrice = convertView.findViewById<View>(R.id.iv_detail_price) as TextView

        image.setImageResource(item.item)
        profile.text = item.profile.toString()
        nickname.text = item.nickname
        addr.text = item.address
        detailTitle.text = item.title
        detailInfo.text = item.info
        detailPrice.text = item.d_price

        return convertView
    }

}