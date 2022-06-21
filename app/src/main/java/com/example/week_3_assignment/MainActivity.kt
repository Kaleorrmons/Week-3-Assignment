package com.example.week_3_assignment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.week_3_assignment.Fragments.RockFragment
import com.example.week_3_assignment.RecyclerView.CustomAdapter
import com.example.week_3_assignment.RecyclerView.ItemsViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert_dialog.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpTabBar()

        checkConnection()

        val service = RetrofitClientInstance.retrofitInstance?.create(ItunesService::class.java)
        val call = service?.getAllItunes()
        call?.enqueue(object : Callback<RockFragment>, retrofit2.Callback<ItemsViewModel> {
            override fun onResponse(
                call: Call<ItemsViewModel>,
                response: Response<ItemsViewModel>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ItemsViewModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    private fun checkConnection() {
        val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = manager.activeNetworkInfo

        if (null != networkInfo){

            if (networkInfo.type == ConnectivityManager.TYPE_WIFI){

                Toast.makeText(this,"Wifi Connected",Toast.LENGTH_LONG).show()
            }
            else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this,"Mobile Data Connected",Toast.LENGTH_LONG).show()
            }
        }
        else{
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.alert_dialog)

            dialog.setCanceledOnTouchOutside(false)

            dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT)

            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.btn_try_again.setOnClickListener{

                recreate()
            }
            dialog.show()
        }
    }

    private fun setUpTabBar()
    {
        val adapter = TabPageAdapter(this, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab)
            {
                viewPager.currentItem = tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }
}