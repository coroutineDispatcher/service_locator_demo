package com.sxhardha.slocator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sxhardha.slocator.R
import kotlinx.android.synthetic.main.fragment_entrance.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel


class EntranceFragment : Fragment() {

    private val catAdapter: CatAdapter by currentScope.inject()

    private val entranceViewModel: EntranceFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entrance, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        entranceViewModel.cats.observe(this, Observer {
            rvCats.adapter = catAdapter
            catAdapter.submitList(it)
        })
    }

}