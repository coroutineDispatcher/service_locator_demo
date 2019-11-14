package com.sxhardha.slocator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_entrance.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class EntranceFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()

    private val viewModelFactory: EntranceVMFactory by instance()

    private val picasso: Picasso by instance()

    private val catAdapter by lazy {
        CatAdapter(picasso)
    }

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

        entranceViewModel.cats.observe(this, Observer {
            rvCats.adapter = catAdapter
            catAdapter.submitList(it)
        })
    }

}