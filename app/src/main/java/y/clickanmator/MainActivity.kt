package y.clickanmator

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : BaseActivity() {

    lateinit var mIvSurfing: ImageView
    lateinit var mRowingBtn:Button
    lateinit var tv_title:TextView
    lateinit var btn_back : Button

    var mMove = 0.0f
    var topMargin = 100f
    var targetTime = 0

    //목표는 350f
    //1초에 10f  < 1초에 20f < 1초에 25f < 1초에 30f

    var Lv1Time = 45
    var Lv2Time = 35
    var Lv3Time = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mIvSurfing = findViewById(R.id.iv_surfing)
        mRowingBtn = findViewById(R.id.btn_click)
        tv_title = findViewById(R.id.tv_title)
        btn_back = findViewById(R.id.btn_back)


        if(intent.hasExtra("key")){
            var getData = intent.getStringExtra("key")
            if(getData!!.equals("lv1")){
                targetTime = Lv1Time
            }else if(getData!!.equals("lv2")){
                targetTime = Lv2Time
            }else if(getData!!.equals("lv3")){
                targetTime = Lv3Time
            }
        }
        initRowing()

        btn_back.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                finish()
            }
        })

        val countDown = object : CountDownTimer((1000 *targetTime).toLong(), 1000) {
            override fun onTick(p0: Long) {
                var countTime = (p0 / 1000)
                tv_title.setText("남은시간  : ${countTime.toString()} 초")
            }
            override fun onFinish() {
                if(mMove==350f){
                    tv_title.setText("도전 성공!!!!!")
                }else{
                    tv_title.setText("실패")
                }
                finishRowing()
            }
        }.start()

        mRowingBtn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                rowing(mMove)
                if(mMove==350f){
                    tv_title.setText("도전 성공!!!!!")
                    finishRowing()
                    countDown.cancel()
                }
                ++mMove
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun rowing(goToX:Float){
        val layoutPrams = LinearLayout.LayoutParams(dpToPx(35.3f).toInt(),dpToPx(52.6f).toInt())
        layoutPrams.setMargins(dpToPx(goToX).toInt(), dpToPx(topMargin).toInt(),0,0)
        mIvSurfing.layoutParams = layoutPrams
        mIvSurfing.visibility = View.VISIBLE
        mIvSurfing.invalidate()
    }

    fun initRowing(){
        val layoutPrams = LinearLayout.LayoutParams(dpToPx(35.3f).toInt(),dpToPx(52.6f).toInt())
        layoutPrams.setMargins(0, dpToPx(topMargin).toInt(),0,0)
        mIvSurfing.layoutParams = layoutPrams
        mIvSurfing.visibility = View.VISIBLE
    }

    fun dpToPx(dp:Float):Float{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,resources.displayMetrics)
    }

    fun finishRowing(){
        btn_back.isEnabled = true //뒤로가기버튼 활성화
        mRowingBtn.isEnabled = false //노젓기 버튼 비활성화
    }

}//class end