package br.edu.infnet.my_thoughts_list.model


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.my_thoughts_list.R

class ThoughtsListAdapter :
    ListAdapter<ListEntity, ThoughtsListAdapter.ThoughtsViewHolder>(THOUGHTS_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThoughtsViewHolder {
        return ThoughtsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ThoughtsViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.thoughts)
    }

    class ThoughtsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvThought: TextView = itemView.findViewById(R.id.tvThought)

        fun bind(text: String?) {
            tvThought.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ThoughtsViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ThoughtsViewHolder(view)
            }
        }
    }

    companion object {
        private val THOUGHTS_COMPARATOR = object : DiffUtil.ItemCallback<ListEntity>() {
            override fun areItemsTheSame(oldItem: ListEntity, newItem: ListEntity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ListEntity, newItem: ListEntity): Boolean {
                return oldItem.thoughts == newItem.thoughts
            }
        }
    }
}
