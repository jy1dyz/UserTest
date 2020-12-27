package kg.study.mlkit.usertest.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kg.study.mlkit.usertest.adapter.UserAdapter
import kg.study.mlkit.usertest.databinding.MainFragmentBinding
import kg.study.mlkit.usertest.db.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private var adapter = UserAdapter()

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
        viewModel.users.observe(viewLifecycleOwner, {
            adapter.refresh(it.toMutableList())
        })
    }

    private fun setupAdapter() = with(binding) {
        rvUsers.adapter = adapter
        adapter.setOnItemClickListener {
            Snackbar.make(requireView(), "User deleted", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun delete(user: User) {
        viewModel.delete(user = user)
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