package com.example.market

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.ItemRecyclerviewBinding

class MyAdapter(val itemList: ArrayList<MyItem>): RecyclerView.Adapter<MyAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
//        return CustomViewHolder(view)
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        holder.photo.setImageResource(itemList.get(position).photo)
//        holder.name.text = itemList.get(position).name
//        holder.addr.text = itemList.get(position).addr
//        holder.price.text = itemList.get(position).price
//        holder.chat.text = itemList.get(position).chat.toString()
//        holder.heart.text = itemList.get(position).heart.toString()
        val currentItem = itemList[position]
        holder.bind(currentItem)

        holder.binding.root.setOnClickListener {
            val context = holder.binding.root.context
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }
    }

    class CustomViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyItem){
            binding.ivItem.setImageResource(item.photo)
            binding.tvTitle.text = item.name
            binding.tvAddr.text = item.addr
            binding.tvPrice.text = item.price
            binding.chatNum.text = item.chat.toString()
            binding.heartNum.text = item.heart.toString()

        }
//        val photo = itemView.findViewById<ImageView>(R.id.iv_Item)
//        val name = itemView.findViewById<TextView>(R.id.tv_title)
//        val addr = itemView.findViewById<TextView>(R.id.tv_addr)
//        val price = itemView.findViewById<TextView>(R.id.tv_price)
//        val chat = itemView.findViewById<TextView>(R.id.chat_num)
//        val heart = itemView.findViewById<TextView>(R.id.heart_num)
    }
}