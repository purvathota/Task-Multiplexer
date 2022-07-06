package com.mananjain.taskmultiplexer.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mananjain.taskmultiplexer.adapters.MemberListItemsAdapter
import com.mananjain.taskmultiplexer.databinding.DialogListBinding
import com.mananjain.taskmultiplexer.models.User

abstract class MembersListDialog(
    context: Context,
    private var list: ArrayList<User>,
    private val title: String = ""
) : Dialog(context) {

    private var adapter: MemberListItemsAdapter? = null
    private var binding: DialogListBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState ?: Bundle())


        /*
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_list, null)
        binding = DialogListBinding.bind(view)
        binding?.root?.let { setContentView(it) }

         */
        binding = DialogListBinding.inflate(layoutInflater)
        binding?.root?.let { setContentView(it) }
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding?.tvTitle?.text = title

        if (list.size > 0) {

            binding?.rvList?.layoutManager = LinearLayoutManager(context)
            adapter = MemberListItemsAdapter(context, list)
            binding?.rvList?.adapter = adapter

            adapter!!.setOnClickListener(object :
                MemberListItemsAdapter.OnClickListener {
                override fun onClick(position: Int, user: User, action:String) {
                    dismiss()
                    onItemSelected(user, action)
                }
            })
        }
    }

    protected abstract fun onItemSelected(user: User, action:String)
}