package com.example.market

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.market.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList = arrayListOf(
            MyItem(R.drawable.sample1, "산지 한달된 선풍기 팝니다", "서울 서대문구 창천동", "1000원", 25, 13),
            MyItem(R.drawable.sample2, "김치냉장고", "인천 계양구 귤현동", "20000원", 28, 8),
            MyItem(R.drawable.sample3, "샤넬 카드지갑", "수성구 범어동", "10000원", 5, 23),
            MyItem(R.drawable.sample4, "금고", "해운대구 우제2동", "10000원", 17, 14),
            MyItem(R.drawable.sample5, "갤럭시Z플립3 팝니다", "연제구 연산제8동", "150000원", 9, 22),
            MyItem(R.drawable.sample6, "프라다 복조리백", "수원시 영통구 원천동", "50000원", 16, 25),
            MyItem(R.drawable.sample7, "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장", "남구 옥동", "150000원", 54, 142),
            MyItem(R.drawable.sample8, "샤넬 탑핸들 가방", "동래구 온천제2동", "180000원", 7, 31),
            MyItem(R.drawable.sample9, "4행정 엔진분무기 판매합니다.", "원주시 명륜2동", "30000원", 28, 7),
            MyItem(R.drawable.sample10, "셀린느 버킷 가방","중구 동화동", "190000원", 6, 40)
        )

        for (item in dataList) {
            // 가격(price)을 천 단위로 콤마(,) 처리
            val priceWithComma = "${String.format("%,d", item.price.replace("원", "").toInt())}원"
            item.price = priceWithComma
        }

        binding.rvitem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        binding.rvitem.addItemDecoration(dividerItemDecoration)
        binding.rvitem.setHasFixedSize(true)
        binding.rvitem.adapter = MyAdapter(dataList)

        binding.ivBell.setOnClickListener {
            notification()
        }
    }

    fun notification(){
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, channelId)
        }else{
            builder = NotificationCompat.Builder(this)
        }

        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다.")

            manager.notify(11, builder.build())
        }
    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("종료하시겠습니까?")
            .setPositiveButton("확인", DialogInterface.OnClickListener { _, _ ->
                finish() // 앱 종료
            })
            .setNegativeButton("취소", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss() // 다이얼로그 닫기
            })

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}