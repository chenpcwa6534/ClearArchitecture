package com.mh.universalscroe.ui.main.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.lib.components.LayoutId
import com.mh.universalscroe.BR
import com.mh.universalscroe.R
import com.mh.universalscroe.base.BaseFragment
import com.mh.universalscroe.ui.main.UIIntent
import com.mh.universalscroe.ui.main.UIState
import com.mh.universalscroe.ui.main.vm.ViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@LayoutId(R.layout.fragment_main)
class MainFragment : BaseFragment(), View.OnClickListener{
    val TAG = "MainFragment"

    private val viewModel by viewModel<ViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        super.onCreateView(inflater, container, savedInstanceState)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.setVariable(BR.state, this.viewModel.getUIState())
        this.binding.setVariable(BR.onclick, this)
        initUIState()
    }

    override fun onClick(view: View?) {
        lifecycleScope.launch{
            when(view!!.id){
                R.id.btn_next -> this@MainFragment.viewModel.getUIIntent().send(UIIntent.Next)
                R.id.btn_refresh -> this@MainFragment.viewModel.getUIIntent().send(UIIntent.Refresh)
                R.id.btn_research -> this@MainFragment.viewModel.getUIIntent().send(UIIntent.Research)
            }
        }
    }

    private fun initUIState(){
        this.viewModel.getUIState().apiResult.observeForever{
            renderPerson(it)
        }

        this.viewModel.getUIState().uiTrans.observeForever { transFragment(it) }
    }

    private fun renderPerson(state: UIState.ApiResult){
        with(state) {
            if (state.isShowProgress) {
                //TODO show progress bar
            } else {
                if (state.result.not()) {
                    AlertDialog.Builder(this@MainFragment.context)
                        .setTitle("網路問題")
                        .setMessage(state.errorMsg)
                        .setPositiveButton("重試") { _, _ ->
                            lifecycleScope.launch {
                                this@MainFragment.viewModel.getUIIntent().send(UIIntent.Refresh)
                            }
                        }
                        .show()
                }
            }
        }


    }
}