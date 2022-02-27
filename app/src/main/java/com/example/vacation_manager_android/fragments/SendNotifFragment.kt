package com.example.vacation_manager_android.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.vacation_manager_android.R

private const val ARG_PARAM1 = "param1"

class SendNotifFragment : Fragment() {
    private var param1: String? = null

    private lateinit var etBody: EditText
    private lateinit var btnEmail: Button
    private lateinit var emailAddresses: Array<String>
    private lateinit var subject: String
    private lateinit var body: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_notif, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables(view)

        btnEmail.setOnClickListener {
            /** Update del edit text **/
            body = etBody.text.toString()

            val selectorIntent = Intent(Intent.ACTION_SENDTO)
            selectorIntent.setData(Uri.parse("mailto:"))

            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            sendIntent.putExtra(Intent.EXTRA_TEXT, body)
            sendIntent.setSelector(selectorIntent)
            activity?.startActivity(Intent.createChooser(sendIntent, "Send email..."));
        }
    }

    private fun initVariables(view: View) {
        etBody = view.findViewById(R.id.et_body)
        btnEmail = view.findViewById(R.id.btn_email)
        // Aqui debe asignar la dir de correo del funcionario
        emailAddresses = arrayOf("mdocarmo@roshka.com")
        subject = "ROSHKA - Notificación de vacaciones cusadas"
        body = etBody.text.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            SendNotifFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}