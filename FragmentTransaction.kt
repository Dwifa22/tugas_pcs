package com.example.pizzaapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTransaction.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTransaction : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvTrx: RecyclerView = view.findViewById(R.id.recyclerTransaksi)
        rvTrx.apply {
            rvTrx.layoutManager = LinearLayoutManager(activity)
            var adapter = TransaksiAdapter()
            rvTrx.adapter = adapter
        }

        val txtOrder: TextView = view.findViewById(R.id.textViewTotal)
        val txtTax: TextView = view.findViewById(R.id.textViewTax)
        val txtTotal: TextView = view.findViewById(R.id.textView13)

        txtOrder.text = TransaksiAdapter.harga.toString()
        txtTax.text = (TransaksiAdapter.harga * 0.10).toString()
        txtTotal.text = (TransaksiAdapter.harga + (TransaksiAdapter.harga * 0.10)).toString()

        val btnPay: Button = view.findViewById(R.id.buttonPay)
        btnPay.setOnClickListener {
            activity?.let {
                val intent = Intent(it, PaymentActivity::class.java)
                it.startActivity(intent)
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentTransaction.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTransaction().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}