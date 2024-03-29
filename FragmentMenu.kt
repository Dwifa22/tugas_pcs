package com.example.pizzaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaapp.client.RetrofitClient
import com.example.pizzaapp.response.menu.MenuResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentMenu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //declare arraylist dari menu response
    private val list = ArrayList<MenuResponse>()



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
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        val rvmenu:RecyclerView = view.findViewById(R.id.recyclerMenu)
        rvmenu.apply {
            rvmenu.layoutManager = GridLayoutManager(activity,2)

            //memanggil retrofit -> panggil fungsi getMenu()
            RetrofitClient.instance.getMenu().enqueue(object : Callback<ArrayList<MenuResponse>>{
                override fun onResponse(
                        call: Call<ArrayList<MenuResponse>>,
                        response: Response<ArrayList<MenuResponse>>
                ) {
                    //list nya dikosongkan untuk diisi data supaya tidak dobel
                    list.clear()
                    //proses pengisian data setelah list dikosongkan
                    response.body()?.let { list.addAll(it) }
                    var adapter = MenuAdapter(list)
                    rvmenu.adapter = adapter
                }

                override fun onFailure(call: Call<ArrayList<MenuResponse>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentMenu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}