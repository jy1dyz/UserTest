package kg.study.mlkit.usertest.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kg.study.mlkit.usertest.adapter.DogsAdapter
import kg.study.mlkit.usertest.adapter.UserAdapter
import kg.study.mlkit.usertest.databinding.MainFragmentBinding
import kg.study.mlkit.usertest.db.model.AppDatabase
import kg.study.mlkit.usertest.db.model.User
import kg.study.mlkit.usertest.db.model.populateDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private var adapter = UserAdapter()
    private var dogsAdapter = DogsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupObservers()
        search()
    }

    private fun setupObservers() = with(binding) {
        viewModel.populateDb()
        viewModel.users.observe(viewLifecycleOwner, {list ->
            Log.w("TAG", "users=$list")
//            list.forEach { user ->
//                viewModel.insert(user)
//            }
            adapter.refresh(list.toMutableList())
        })
//        viewModel.getAllUsers().observe(viewLifecycleOwner, {
//            adapter.refresh(it.toMutableList())
//        })
//        viewModel.usersAndDogs.observe(viewLifecycleOwner, {
//            adapter.refresh(it.toMutableList())
//        })
//        viewModel.usersWithDogs.observe(viewLifecycleOwner, {
//            adapter.refresh(it.toMutableList())
//        })

    }

    private fun setupAdapter() = with(binding) {
        rvUsers.adapter = adapter
        adapter.setOnItemClickListener {
//            viewModel.delete(it)
//            Snackbar.make(requireView(), "User deleted", Snackbar.LENGTH_SHORT).show()
//            viewModel.users.observe(viewLifecycleOwner, {list ->
//                adapter.refresh(list.toMutableList())
//            })
            delete(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun delete(user: User) = with(binding) {
        viewModel.delete(user)
    }

    private fun search() {

        binding.search.queryHint = "Find User"
        binding.search.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0!!.length > 2) {
                    viewModel.findUserWithName(p0)
                    adapter.find(p0)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }
}