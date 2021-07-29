package com.cookandroid.carrot_market.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.carrot_market.R
import com.cookandroid.carrot_market.adapter.GridAdapter
import com.cookandroid.carrot_market.adapter.MyNearNewsAdapter
import com.cookandroid.carrot_market.adapter.RecommendAdapter
import com.cookandroid.carrot_market.databinding.FragmentMyNearBinding
import com.cookandroid.carrot_market.info.GridInfo
import com.cookandroid.carrot_market.info.MyNearNewsInfo
import com.cookandroid.carrot_market.info.RecommendInfo

class FragmentMyNear : Fragment() {
    private lateinit var binding : FragmentMyNearBinding
    private var gridList = ArrayList<GridInfo>()
    private var recommendList = ArrayList<RecommendInfo>()
    private var newsList = ArrayList<MyNearNewsInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gridList.add(GridInfo(R.drawable.grid1,"쿠폰북"))
        gridList.add(GridInfo(R.drawable.grid2,"친환경 지도"))
        gridList.add(GridInfo(R.drawable.grid3,"카페"))
        gridList.add(GridInfo(R.drawable.grid4,"동네알바"))
        gridList.add(GridInfo(R.drawable.grid5,"동네 구인구직"))
        gridList.add(GridInfo(R.drawable.grid6,"과외/클래스"))
        gridList.add(GridInfo(R.drawable.grid7,"농수산물"))
        gridList.add(GridInfo(R.drawable.grid8,"부동산"))
        gridList.add(GridInfo(R.drawable.grid9,"중고차"))
        gridList.add(GridInfo(R.drawable.grid10,"청소"))
        gridList.add(GridInfo(R.drawable.grid11,"세탁"))
        gridList.add(GridInfo(R.drawable.grid12,"당근 장바구니"))
        gridList.add(GridInfo(R.drawable.grid13,"지역광고"))

        newsList.add(
            MyNearNewsInfo(
                R.drawable.my_near_news_img1,"~녹지속 공간이야기~~~일산의 장점입니다.","오늘은 코로나 4차적용 첫날입니다~ 거리는 유난히 사람들이 줄었습니다. 무엇보다 상황이 좋지",
                R.drawable.my_near_news_profile_img1,"공간이야기","대화동")
        )
        newsList.add(
            MyNearNewsInfo(
                R.drawable.my_near_news_img2,"네이버밴드 크크셀렉샵 가입하면 5000원 할인 쿠폰","유럽 미국 일본 빈티지의류 악세사리를 # 도매가 에 구매하는 곳이에요. 이쁜 옷을 음청 착용해 보실 수",
                R.drawable.my_near_news_profile_img2,"크크","일산1동")
        )
        newsList.add(
            MyNearNewsInfo(
                R.drawable.my_near_news_img3,"2021GREENVACANCE-8월 원데이 클래스 진행합니다.","8월 원데이클래스는 비대면 줌수업으로 진행합니다. 가족,친구,연인과 한 공간에서 편하고 재밌게 만들어 보세요",
                R.drawable.my_near_news_profile_img3,"라라그레이스","대화동")
        )

        recommendList.add(
            RecommendInfo(
                R.drawable.my_near_recommend_store_img1,"크린업24 셀프빨래방 야당역점","안녕하세요. 크린업24 셀프빨래방 야당역점 입니다.",4,6,
        "비가 많이와서 그런지~~ 울 아들램이 운화가 다 젖어버린거 아니에요?? 그래서","예준맘")
        )
        recommendList.add(
            RecommendInfo(
                R.drawable.my_near_recommend_store_img2,"쪼리청바지","여자청반바지 이월상품5000원행사 한장에 15000원에 드립니다.",6,57,
        "오늘 동생이랑 다녀왔는데 너무너무 만족하고 왔습니당ㅎㅎ 일단 사장님이 너무 친절하세요","버쮸")
        )
        recommendList.add(
            RecommendInfo(
                R.drawable.my_near_recommend_store_img3,"신화공조시스템","에어컨.이전설치.중고판매.매입 무료철거 전문점",16,36,
        "사장님 오늘 수고 많으셨습니다. 많은 업체들중에 어디를 택해야 될지 몰라서 고민했는데","어메이징")
        )
        recommendList.add(
            RecommendInfo(
                R.drawable.my_near_recommend_store_img4,"내방원룸이사","화물운송 3만원~ 일반이사 10만원~ 반포이사 12만원~ 일산",36,74,
            "코로나로 인해 마스크 착용하며 일하시며 힘드실텐데 짜증 한번 없이 웃으면서 이사해 주셔서 감사해요","블랙")
        )
        recommendList.add(
            RecommendInfo(
                R.drawable.my_near_recommend_store_img5,"조선팔도 농부 알리미","\"농부와 소비자가 만나는 직거래 장터\" 검색해 보세요",126,426,
            "저희엄마가 참외엄청 좋아하셔서 시켜드렸는데 넘맛있다구 넘좋아하셨어요!!","소중한인연")
        )
        recommendList.add(
            RecommendInfo(
                R.drawable.my_near_recommend_store_img6,"열정국밥","대한민국 최초 돼지국밥에 들어가는 국물을 시원하게 만들었어요",5,27,
            "당근을 통해서 알게된 감성부대찌개~~~ 어쩜 이걸 이제야 알았을까요 정말 맛있어요!","가을햇살")
        )
        recommendList.add(
            RecommendInfo(
                R.drawable.my_near_recommend_store_img7,"부부용달","부부가 작업하는 개인 용달입니다. 고객의 편에서 생각하겠습니다.",232,330,
            "돈 아낀다고 용달이사 해놓고 쏟아져 나오는 추가 저희 짐들로 두명이상이서 꼼꼼하게 작업합니다.","가위바위보")
        )
        recommendList.add(
            RecommendInfo(
                R.drawable.my_near_recommend_store_img8,"류군의중고차","아내와 고객님께 부끄럽지 않은 인증후기를 남기기 위해 열심히",5,12,
            "류군께서 친절하시고 솔직한 분인듯 합니다. 밧데리가 불안해 보인다고 말씀해 주시고 정말 친절합니다.","한뫼5656")
        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMyNearBinding.inflate(layoutInflater)

        binding.fragmentMyNearGridRecyclerview.layoutManager = GridLayoutManager(context,4)
        binding.fragmentMyNearGridRecyclerview.adapter = GridAdapter(gridList)

        binding.fragmentMyNearNewsRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.fragmentMyNearNewsRecyclerview.adapter = MyNearNewsAdapter(newsList)

        binding.fragmentMyNearRecommendRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.fragmentMyNearRecommendRecyclerview.adapter = RecommendAdapter(recommendList)

        return binding.root
    }

}
