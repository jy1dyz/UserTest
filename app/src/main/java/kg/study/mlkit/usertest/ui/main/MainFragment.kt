package kg.study.mlkit.usertest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kg.study.mlkit.usertest.adapter.UserAdapter
import kg.study.mlkit.usertest.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private var adapter = UserAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupObservers()
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

}