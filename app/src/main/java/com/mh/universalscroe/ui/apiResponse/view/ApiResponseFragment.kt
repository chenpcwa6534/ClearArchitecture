package com.mh.universalscroe.ui.apiResponse.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.lib.components.LayoutId
import com.mh.universalscroe.BR
import com.mh.universalscroe.R
import com.mh.universalscroe.base.BaseFragment
import com.mh.universalscroe.ui.apiResponse.vm.ViewModel
import kotlinx.android.synthetic.main.fragment_api_response.*
import org.koin.android.viewmodel.ext.android.viewModel

@LayoutId(R.layout.fragment_api_response)
class ApiResponseFragment : BaseFragment() {
    val TAG = "ApiResponseFragment"

    private val viewModel by viewModel<ViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        super.onCreateView(inflater, container, savedInstanceState)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.setVariable(BR.viewModel, this.viewModel.getUIState())

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){ android.R.id.home -> popBackFragment() }
        return super.onOptionsItemSelected(item)
    }

}