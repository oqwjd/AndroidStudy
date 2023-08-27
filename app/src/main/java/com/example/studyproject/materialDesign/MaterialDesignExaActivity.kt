package com.example.studyproject.materialDesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityMaterialDesignExaBinding
import com.example.studyproject.listViewAndRecycleView.Fruit
import com.google.android.material.snackbar.Snackbar

class MaterialDesignExaActivity : AppCompatActivity() {
    private lateinit var viewBinder:ActivityMaterialDesignExaBinding
    private var fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design_exa)
        viewBinder = ActivityMaterialDesignExaBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
        setSupportActionBar(viewBinder.MaterialDesignExaToolbar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.baseline_menu_24)
        }
        viewBinder.MDNavView.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.navCall -> Toast.makeText(this,"you clicked Call button",Toast.LENGTH_SHORT).show()
                R.id.navPeople -> Toast.makeText(this,"you clicked Friend button",Toast.LENGTH_SHORT).show()
                R.id.navEMail -> Toast.makeText(this,"you clicked Email button",Toast.LENGTH_SHORT).show()
                R.id.navSetting -> Toast.makeText(this,"you clicked Setting button",Toast.LENGTH_SHORT).show()
                R.id.navLocation -> Toast.makeText(this,"you clicked Location button",Toast.LENGTH_SHORT).show()
            }
            viewBinder.MDDrawerLayout.closeDrawers()
            true
        }

        viewBinder.MDFloatActionBar.setOnClickListener{ view->
            Snackbar.make(view,"Data Delete",Snackbar.LENGTH_SHORT)
                .setAction("Undo"){
                    Toast.makeText(this,"SnackBar Undo",Toast.LENGTH_SHORT).show()
                }
                .show()
            Toast.makeText(this,"you clicked FAB",Toast.LENGTH_SHORT).show()
        }
        initFruits()
        val manager = GridLayoutManager(this,2)
        viewBinder.MDCardRecycleFruitView.layoutManager = manager
        viewBinder.MDCardRecycleFruitView.adapter = FruitItemsRecyclerAdapter(this,fruitList)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> viewBinder.MDDrawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    private fun initFruits(){
        repeat(10){
            fruitList.add(Fruit("apple",R.drawable.apple_pic))
            fruitList.add(Fruit("orange",R.drawable.orange_pic))
            fruitList.add(Fruit("strawberry",R.drawable.strawberry_pic))
            fruitList.add(Fruit("watermelon",R.drawable.watermelon_pic))
        }
    }
}