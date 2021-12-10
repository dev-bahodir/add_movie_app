package dev.bahodir.lesson26thtask1thmovieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.bahodir.lesson26thtask1thmovieapp.databinding.RvLayoutBinding
import dev.bahodir.lesson26thtask1thmovieapp.user.User

class RV(var list: MutableList<User>, var listener: OnItemTouchClickListener) :
    RecyclerView.Adapter<RV.VH>() {

    inner class VH(var rvLayoutBinding: RvLayoutBinding) :
        RecyclerView.ViewHolder(rvLayoutBinding.root) {
        fun onBind(user: User, position: Int) {
            rvLayoutBinding.apply {
                movieName.text = user.movieName
                movieAuthors.text = user.movieAuthors
                movieDate.text = user.movieDate

                edit.setOnClickListener {
                    listener.editMovie(user, position, it)
                }

                delete.setOnClickListener {
                    listener.deleteMovie(user, position, it)
                }

                itemView.setOnClickListener {
                    listener.itemClick(user, position,it)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(RvLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var user = list[position]
        holder.onBind(user, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemTouchClickListener {

        fun deleteMovie(user: User, position: Int, view: View)

        fun editMovie(user: User, position: Int, view: View)

        fun itemClick(user: User, position: Int, view: View)
    }
}