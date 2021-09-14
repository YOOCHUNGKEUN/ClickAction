package y.clickanmator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity:Activity(),View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    
        val btnlv_1 = findViewById<Button>(R.id.btn_lv1)
        val btnlv_2 = findViewById<Button>(R.id.btn_lv2)
        val btnlv_3 = findViewById<Button>(R.id.btn_lv3)
        btnlv_1.setOnClickListener(this)
        btnlv_2.setOnClickListener(this)
        btnlv_3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var intent = Intent(this,MainActivity::class.java)
        when(v!!.id){
            R.id.btn_lv1 ->{
                intent.putExtra("key","lv1")
            }
            R.id.btn_lv2 ->{
                intent.putExtra("key","lv2")
            }
            R.id.btn_lv3 ->{
                intent.putExtra("key","lv3")
            }
        }
        startActivity(intent)
    }

}//class end