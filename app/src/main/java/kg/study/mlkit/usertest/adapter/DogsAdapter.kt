package kg.study.mlkit.usertest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.study.mlkit.usertest.databinding.DogItemBinding
import kg.study.mlkit.usertest.databinding.ItemUserBinding
import kg.study.mlkit.usertest.db.model.User
import kg.study.mlkit.usertest.db.model.UserAndDog
import kg.study.mlkit.usertest.db.model.UserWithDogs

class DogsAdapter: RecyclerView.Adapter<DogsAdapter.DogHolder>() {

    private var list = mutableListOf<UserWithDogs>()
    private lateinit var binding: DogItemBinding
    private var onItemClickListener: ((item: UserWithDogs) -> Unit)? = null

    inner class DogHolder(private val binding: DogItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserWithDogs) = with(binding) {
            dogName.text = user.dogs[adapterPosition].name
        }
    }

    fun refresh(newList: MutableList<UserWithDogs>) {
        this.list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DogItemBinding.inflate(inflater, parent, false)
        return DogHolder(binding)
    }

    override fun onBindViewHolder(holder: DogHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun setOnItemClickListener(listener: (item: UserWithDogs) -> Unit) {
        this.onItemClickListener = listener
    }

}