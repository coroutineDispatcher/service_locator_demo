package com.sxhardha.slocator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.sxhardha.slocator.R
import com.sxhardha.slocator.SlocatorApplication
import com.sxhardha.slocator.di.MyComponent
import kotlinx.android.synthetic.main.fragment_entrance.*
import javax.inject.Inject


class EntranceFragment : Fragment() {

    private val component: MyComponent = SlocatorApplication.getAppComponent()

    @Inject
    lateinit var viewModelFactory: EntranceVMFactory

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var catAdapter: CatAdapter

    private val entranceViewModel: EntranceFragmentViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entrance, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        component.inject(this)
        entranceViewModel.cats.observe(this, Observer {
            rvCats.adapter = catAdapter
            catAdapter.submitList(it)
        })
    }

}