package com.malakinfo.res

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.country_child.view.*

class MainActivity : AppCompatActivity() {
    lateinit var country_list : RecyclerView
    var countries:MutableList<String> = ArrayList()
    var displayList:MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
        country_list = findViewById(R.id.country_list) as RecyclerView
        country_list.layoutManager = LinearLayoutManager(this)
        country_list.adapter = countryAdapter(displayList,this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        if(searchItem != null){
            val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
            val editText = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if(newText!!.equals(this)) {
                        displayList.clear()

                        val search = newText.toLowerCase()
                        countries.forEach{
                            if (it.toLowerCase().contains(search)){
                                displayList.add(it)
                            }
                        }

                    }else{
                        displayList.clear()
                        displayList.addAll(countries)
                        country_list.adapter!!.notifyDataSetChanged()
                    }
                        return true
                }
            })

        }
        return super.onCreateOptionsMenu(menu)
    }

    class countryAdapter(items : List<String>,ctx:Context): RecyclerView.Adapter<countryAdapter.ViewHolder>(){

        var list = items
        var context = ctx



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.country_child,parent,false))

        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.name.text = list.get(position)
        }
        class ViewHolder(v:View) : RecyclerView.ViewHolder(v){
            val name = v.country_name

        }


    }


    fun loadData(){
        countries.add("Afghanistan")
        countries.add("Albania")
        countries.add("Austria")
        countries.add("Belgium")
        countries.add("Bolivia")
        countries.add("Bulgaria")
        countries.add("Chad")
        countries.add("Comoros")
        countries.add("India")
        countries.add("Djibouti")
        countries.add("Dominica")
        countries.add("Estonia")
        countries.add("France")
        countries.add("Gabon")
        countries.add("Germany")
        countries.add("India")
        countries.add("India")
        countries.add("India")
        countries.add("India")
        countries.add("India")
        displayList.addAll(countries)



    }

}
