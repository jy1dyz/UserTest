package kg.study.mlkit.usertest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.study.mlkit.usertest.databinding.ItemUserBinding
import kg.study.mlkit.usertest.db.model.User
import kg.study.mlkit.usertest.db.model.UserAndDog
import kg.study.mlkit.usertest.db.model.UserWithDogs

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var list = mutableListOf<User>()
    private var list2 = mutableListOf<UserAndDog>()
    private var list3 = mutableListOf<UserWithDogs>()
    private lateinit var binding: ItemUserBinding
    private var onItemClickListener: ((item: UserWithDogs) -> Unit)? = null

    inner class UserHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserWithDogs) = with(binding) {
            tvFirstName.text = user.user.firstName
            tvLastName.text = user.user.lastName
            var stringBuilder  = ""
            user.dogs.forEach { dog ->
                stringBuilder += dog.name.plus(" ")
            }
            tvDogName.text = stringBuilder

            userContainer.setOnClickListener {
//                delete(user)
                onItemClickListener?.invoke(user)
            }
        }
    }
    fun delete(user: User) {
        list.remove(user)
        notifyDataSetChanged()
    }

    fun refresh(newList: MutableList<UserWithDogs>) {
        this.list3 = newList
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
        holder.bind(list3[position])
    }

    override fun getItemCount(): Int {
        return list3.size
    }

    fun getUserAt(position: Int): User {
        return list[position]
    }

    fun setOnItemClickListener(listener: (item: UserWithDogs) -> Unit) {
        this.onItemClickListener = listener
    }

}