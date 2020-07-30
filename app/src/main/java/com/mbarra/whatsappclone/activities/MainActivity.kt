package com.mbarra.whatsappclone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mbarra.whatsappclone.R
import com.mbarra.whatsappclone.adapters.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val firebaseDb = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()

    private var mSectionPagerAdapter: SectionPagerAdapter? = null

//    private val chatsFragment = ChatsFragment()
//
//    companion object {
//        const val PARAM_NAME = "name"
//        const val PARAM_PHONE = "phone"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        chatsFragment.setFailureCallbackListener(this)

        //Untuk mengeset tab yang telah di manipulasi
        setSupportActionBar(toolbar)
        mSectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)

        container.adapter = mSectionPagerAdapter // Untuk menampilkan isi dari mSectionPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        //Untuk memberikan pemberitahuan kalau tab bisa diganti(dislide)
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
        //Untuk memberikan pemberitahuan kalau tab bisa diganti(diklik)
        tabs.getTabAt(1)?.select()
        //Untuk memberitahukan kalau tab hanya bisa terpilih/tertampil 1
//      resizeTabs()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> fab.hide()
                    1 -> fab.show()
                    2 -> fab.hide()
                }
            }

        })

//        fab.setOnClickListener {
//            onNewChat()
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // membuat opsi menu menuInflater.inflate(R.menu.menu_main, menu) // menghubungkan menu dari return true // directory menu dengan activity
        menuInflater.inflate(R.menu.menu_main, menu)
        return true // directory menu dengan activity
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // memberikan aksi menu
        when (item.itemId) {
            R.id.action_profile -> onProfile()
            R.id.action_logout -> onLogout()
        }
        return super.onOptionsItemSelected(item) }
    // ketika menu dengan Id tertentu diklik akan // menjalankan fungsi logout
    private fun onLogout() {
        firebaseAuth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun onProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

//    fun setFailureCallbackListener(listener: FailureCallback) {
//        failureCallback = listener
//    }

}