package kg.study.mlkit.usertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kg.study.mlkit.usertest.db.model.AppDatabase
import kg.study.mlkit.usertest.db.model.populateDb
import kg.study.mlkit.usertest.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
//        reCreateDatabase()
    }

    private fun reCreateDatabase() {
        GlobalScope.launch(Dispatchers.IO) {
            populateDb(AppDatabase.instance(this@MainActivity))
        }
    }
}