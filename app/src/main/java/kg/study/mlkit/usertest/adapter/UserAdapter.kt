package kg.study.mlkit.usertest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.study.mlkit.usertest.databinding.ItemUserBinding
import kg.study.mlkit.usertest.db.model.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var list = mutableListOf<User>()
    private lateinit var binding: ItemUserBinding
    private var onItemClickListener: ((item: User) -> Unit)? = null

    inner class UserHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = with(binding) {
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName

            userContainer.setOnClickListener {
                list.remove(user)
                notifyDataSetChanged()
                onItemClickListener?.invoke(user)
            }
        }
    }
    fun delete(user: User) {
        val list2 = list.toMutableList()
        list2.remove(user)
        notifyDataSetChanged()
    }

    fun refresh(newList: MutableList<User>) {
        this.list = newList
        notifyDataSetChanged()
    }

    fun find(value: String) {
       val filter = this.list.filter {
           it.firstName == value
       }
        list.clear()
        list.addAll(filter)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(listener: (item: User) -> Unit) {
        this.onItemClickListener = listener
    }

}