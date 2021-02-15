<template>
    <div class="container">
        <!-- search bar -->
        <div class="search">
            <input type="text" class="searchTerm" placeholder="문화재 또는 지역을 검색해 주세요" v-model="keyword" @keypress.enter="keywordMarker(keyword)">
            <button type="" class="searchButton" @click="keywordMarker(keyword)">
                <i class="fa fa-search"></i>
            </button>
        </div>
        <!-- 카카오 맵 -->
        <vue-daum-map
        id="map"
        :appKey="appKey"
        :center.sync="center"
        :level.sync="level"
        :mapTypeId="mapTypeId"
        :libraries="libraries"
        @load="onLoad()"
        class= "kakaomap"
        />
        <!-- 문화재 상세정보 모달 -->
        <div id="myModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <div class="detailimagediv">
                    <img id="detailimg" src="" alt="" class="detailimage">
                </div>
                <span class="close">&times;</span>
                <div class="detailcontents">
                    <div class="detailtitle east-sea-Dokdo"><p id="detailtitle">문화재 명</p></div>
                    <div class="detailkind"><p id="detailkind" class="jua">문화재 종류</p></div>
                    <div class="detaildescription">
                        <p id="detailcontent" class="yeon">문화재 설명</p>
                    </div>
                    <div class="detailaddress"><p id="detailaddress" class="jua">문화재 주소</p></div>
                </div>
                
            </div>
        </div> 
        <!-- 종료선 -->
    </div>
</template>


<script>
import VueDaumMap from 'vue-daum-map';
import { heritages } from "@/api/heritage.js";
// 문화재 정보 API 상세 정보 요청을 위한 세팅
import axios from 'axios';
import { API_BASE_URL } from '@/config/index.js';

export default {
    name: 'Kakaomap',
    components: {
        VueDaumMap,
    },
    data: () => ({
        // 카카오맵을 위한 data instances
        appKey: '2e4b87e7a16709ce4966d35d9caaad46',
        center: { lat: 36.2683, lng: 127.6358 }, // 지도의 중심 좌표
        level: 13, 
        mapTypeId: VueDaumMap.MapTypeId.NORMAL,
        libraries: ['services', 'clusterer', 'drawing'],
        map: null,
        // keyword 검색을 위한 data instances
        keyword: '',
        // 공지사항 창 boolean
        isClick: false,
        heritages: [],
    }),
    beforeCreate () {
        this.isClick=false
    },
    methods: {
        // 페이지에 최초 맵을 생성할 때 DB에 담긴 모든 문화유적의 마커를 깔고 클러스트로 표현하며 시작한다.
        onLoad () {
            var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
                    center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
                    level : 13 // 지도의 확대 레벨
                });
            this.map = map;
            // 클러스터 생성을 위한 json 데이터 받아오기
            // const addressData = require('@/assets/clustertest.json');
            // 문화재 데이터 axios 요청해서 받기
            heritages((response) => {
                // cluster 생성 methods 실행
                this.setMarkerCluster(response.data, map)
            })
        },
        // 마커 클러스터러를 생성.
        // async 사용해 동기처리.
        setMarkerCluster: async (data, map) => {
            let clusterer = new window.kakao.maps.MarkerClusterer({
                map: map,
                averageCenter: true,
                minLevel: 10,
                calculator: [10,50], // 클러스터의 크기 구분 값, 각 사이값마다 설정된 text나 style이 적용된다
                styles: [{ // calculator 각 사이 값 마다 적용될 스타일을 지정한다
                        width : '30px', height : '30px',
                        background: '#D7CCC8',
                        borderRadius: '15px',
                        color: '#000',
                        textAlign: 'center',
                        fontWeight: 'bold',
                        lineHeight: '31px',
                    },
                    {
                        width : '40px', height : '40px',
                        background: '#37474F',
                        borderRadius: '20px',
                        color: '#FFF',
                        textAlign: 'center',
                        fontWeight: 'bold',
                        lineHeight: '41px'
                    },
                    {
                        width : '50px', height : '50px',
                        background: '#D7CCC8',
                        borderRadius: '25px',
                        color: '#000',
                        textAlign: 'center',
                        fontWeight: 'bold',
                        lineHeight: '51px'
                    }, // styles 종료선.
                ] // clusterer 종료선.
            });
            // 데이터에서 좌표 값을 가지고 마커를 표시합니다
            let markers = data.slice(0,500).map(
                (singleheritage) => {
                // 마커 이미지를 커스텀하기 위한 코드
                var imageSrc = require('@/assets/heritage.png'), // 마커이미지의 주소입니다    
                    imageSize = new kakao.maps.Size(50, 40), // 마커이미지의 크기입니다
                    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
                // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

                // 커스텀 오버레이가 표시될 위치입니다 
                var markerPosition = new kakao.maps.LatLng(singleheritage.latitude, singleheritage.longitude);  

                // 커스텀 오버레이를 생성합니다
                var customOverlay = new kakao.maps.CustomOverlay({
                    map: map,
                    position: markerPosition,
                    yAnchor: 10
                });
                // map에 커스텀오버레이 표시
                customOverlay.setMap(map);
                    // markers에 담길 position과 image 값들을 반환합니다.
                return new window.kakao.maps.Marker({
                    // marker에 담고싶은 정보들 data 이용해서 이 부분부터 추가.(중요)
                    // position: new window.kakao.maps.LatLng(singleheritage.latitude, singleheritage.longitude),
                    position: markerPosition,
                    image: markerImage,
                    clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
                });
            });
            // 마커에 마우스오버 등록을 위한 코드 시작.
            for (var i = 0; i < markers.length; i ++) {
                    var marker = markers[i]
                    var singledata = data[i]
                    // 마커에 표시할 인포윈도우 생성
                    // 인포위도우 커스터마이징(정보 및 이미지 담고 구조랑 디자인 짜기)
                    // image 요청 url 파라미터 생성
                    var ccbaKdcd = singledata.ccba_kdcd
                    var ccbaAsno = singledata.ccba_asno
                    var ccbaCtcd = singledata.ccba_ctcd   
                    var imageSrc= singledata.img_url
    
                    // 변수명 사용을 위해 분기점을 나눠준다.
                    var content0 = '<div class="infoHeader jua"><text>클릭하면 상세정보를 볼 수 있어요</text></div>'
                    var content1 = '<img class="infoImg" src="'
                    var content2 = imageSrc
                    var content3 = '">'
                    var content4 = '<div class="infoTitle"><text class="east-sea-Dokdo">'
                    var content5 = singledata.ccba_mnm
                    var content6 = '</text></div>'
    
                    var infowindow = new kakao.maps.InfoWindow({
                        content: content0+content1+content2+content3+content4+content5+content6 // 인포윈도우에 표시할 내용
                    });
                    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
                    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
                    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
                    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
                    // 마커에 클릭이벤트를 등록합니다
                    kakao.maps.event.addListener(marker, 'click', makeClickListner(ccbaKdcd,ccbaAsno,ccbaCtcd, imageSrc));
                }
            // 마우스오버 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
            function makeOverListener(map, marker, infowindow) {
                return function() {
                    infowindow.open(map, marker);
                };
            }
            // 마우스아웃 인포윈도우를 닫는 클로저를 만드는 함수입니다 
            function makeOutListener(infowindow) {
                return function() {
                    infowindow.close();
                };
            }
            // 클릭이벤트용 인포윈도우를 표시하는 클로저를 만드는 함수
            function makeClickListner(ccbaKdcd,ccbaAsno,ccbaCtcd,img) {
                return async () => {

                    // 마커 클릭시 모달 열람 기능 추가
                    var modal = document.getElementById("myModal");
                    modal.style.display = "block";
                    // When the user clicks on <span> (x), close the modal
                    var span = document.getElementsByClassName("close")[0];
                    span.onclick = function() {
                        modal.style.display = "none";
                    }
                    // When the user clicks anywhere outside of the modal, close it
                    window.onclick = function(event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                        }
                    }

                    // axios 요청
                    var name = null; 
                    var kind = null; 
                    var content = null; 
                    var loc = null
                    const getdetail = async () => {
                        try {
                            await axios.get(`${API_BASE_URL}/heritage?asno=${ccbaAsno}&ctcd=${ccbaCtcd}&kdcd=${ccbaKdcd}`)
                            .then((res) => {
                                name = res.data.ccba_mnm
                                kind = res.data.mcode_name
                                content = res.data.content
                                loc = res.data.ccba_lcad
                            })
                        } catch(err) {console.log(err)}
                    }
                    await getdetail()
                    // 모달 내용 변경
                    // 이미지 변경
                    var detailimg = document.getElementById("detailimg");
                    detailimg.src = img;
                    // 문화재명
                    var detailtitle = document.getElementById("detailtitle");
                    detailtitle.innerHTML = name;
                    // 문화재 종류
                    var detailkind = document.getElementById("detailkind");
                    detailkind.innerHTML = "분류: "+kind;
                    // 문화재 상세설명
                    var detailcontent = document.getElementById("detailcontent");
                    detailcontent.innerHTML = content;
                    //문화재 주소
                    var detailaddress = document.getElementById("detailaddress")
                    detailaddress.innerHTML = "위치: "+loc
                };
            }
            // clusterer에 마커들을 추가.(중요)
            clusterer.addMarkers(markers)
        },
        
        // keyword 검색을 위한 Method
        keywordMarker: async (keyword) => {
            var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                mapOption = {
                    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
                    level: 3 // 지도의 확대 레벨
                };  

            // 지도를 생성합니다    
            var map = new kakao.maps.Map(mapContainer, mapOption); 

            // 장소 검색 객체를 생성합니다
            var ps = new kakao.maps.services.Places();

            // const getheritages = async() => {
            //     try {
            //         await axios.get(`${API_BASE_URL}/heritages/${this.keyword}`)
            //         .then((res)=>{
            //             heritages = res.data
            //         })
            //     } catch(err) {console.log(err)}
            // }
            // await getheritages()

            // 키워드로 장소를 검색합니다
            ps.keywordSearch(keyword, placesSearchCB);
            // var heritages = []
            // 문화재 정보 검색
            const getheritages = async() => {
                try {
                    await axios.get(`${API_BASE_URL}/heritages/${keyword}`)
                    .then((res)=>{
                        heritages = res.data
                    })
                } catch(err) {console.log(err)}
            }
            await getheritages()
            // 키워드 검색 완료 시 호출되는 콜백함수 입니다
            function placesSearchCB (data, status) {
                if (status === kakao.maps.services.Status.OK) {
                    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                    // LatLngBounds 객체에 좌표를 추가합니다
                    var bounds = new kakao.maps.LatLngBounds();

                    for (var i=0; i<data.length; i++) {
                        displayMarker(data[i]);    
                        bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
                    }       

                    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                    map.setBounds(bounds);
                } 
            }
            // 지도에 마커를 표시하는 함수입니다(마커 표시와 동시에 마우스오버와 클릭 이벤트도 같이 생성.)
            function displayMarker(place) {
                
                // 마커를 생성하고 지도에 표시합니다
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: new kakao.maps.LatLng(place.y, place.x),
                    clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
                });
                // 마커에 표시할 인포윈도우 생성
                // 인포위도우 커스터마이징(정보 및 이미지 담고 구조랑 디자인 짜기)
                var imageSrc = require('@/assets/exampleImg.webp')
                // 전체 content 구조 예시
                // '<div><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png"></div>' +
                // '<div class="markertitlediv"><text class="gugi markertitletext">제목</text></div>'
                // 변수명 사용을 위해 분기점을 나눠준다.
                var content1 = '<img class="infoImg" src="'
                var content2 = imageSrc
                var content3 = '">'
                var content4 = '<div class="infoTitle"><text class="jua">경주 월성</text></div>'

                var infowindow = new kakao.maps.InfoWindow({
                    content: content1+content2+content3+content4 // 인포윈도우에 표시할 내용
                });
                // 마커에 마우스오버 이벤트를 등록합니다
                kakao.maps.event.addListener(marker, 'mouseover', function() {
                // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                    infowindow.open(map, marker);
                });

                // 마커에 마우스아웃 이벤트를 등록합니다
                kakao.maps.event.addListener(marker, 'mouseout', function() {
                    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
                    infowindow.close();
                });

                // 마커에 클릭이벤트를 등록하기 위한 코드 시작.
                // 마커에 클릭이벤트를 등록합니다
                kakao.maps.event.addListener(marker, 'click', function() {
                    // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
                    var iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
                    var imageSrc = require('@/assets/exampleImg.webp')
                    var content1 = '<img class="detailImg" src="'
                    var img = imageSrc
                    var content2 = '">'
                    var content3 = '<div class="detailTitle"><text class="jua">'
                    var title = '경주 월성'
                    var content4 = '</text></div>'
                    var content5 = '<div class="detailContent"><text class="jua">'
                    var descrip = '내용: 경주 월성은 ~년도에 제작된 ~이며, 누구누구 왕이 어저구저저구 ~~~~~~~~~~~~~~~~~~~~~~~~'
                    var content6 = '</text></div>'
                    var infowindow = new kakao.maps.InfoWindow({
                        content: content1+img+content2+content3+title+content4+content5+descrip+content6, // 인포윈도우에 표시할 내용
                        removable: iwRemoveable
                    });
                    infowindow.open(map, marker)
                });
            } // displayMarker 종료부분
        }, // keyword Marker 종료부분
    }, // methods 종료부분 
}
</script>

<style>
/* search bar */
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

body{
  background: #f2f2f2;
  font-family: 'Open Sans', sans-serif;
}

.search {
  width: 90%;
  position: relative;
  display: flex;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 3%;
}

.searchTerm {
  width: 100%;
  border: 4px solid #263238;
  border-right: none;
  padding: 5px;
  height: 50px;
  border-radius: 5px 3px 5px 5px;
  outline: none;
  color: #263238;
}

.searchTerm:focus{
  color: #263238;
}

.searchButton {
  width: 50px;
  height: 50px;
  border-left: 3px solid #263238;
  background: #263238;
  text-align: center;
  color: #fff;
  border-radius: 3px 5px 5px 5px;
  cursor: pointer;
  font-size: 25px;
}

/* kakaomap */
.kakaomap {
    width:90rem; height:60rem; margin-left:auto; margin-right:auto;
    z-index: 0;
}

/* 마우스오버 Info Window */
.infoHeader {
    font-size: 25px;
    color: black;
    margin: 0.5rem 0rem 0rem 0rem;

}
.infoImg {
    width: auto; height: auto;
    max-width: 30rem;
    max-height: 20rem;
    padding: 0.5rem 3rem 1rem 3rem;
}
.infoTitle {
    border: 3px solid #24303F;
    background-color: #24303F;
    color: #E2D6CE;
    margin: 0;
    font-size: 40px;
}

/* 문화재 상세정보 조회 창 */
/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width:100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  border-radius: 20px;
  width: 80%;
  height: 70%;
}
/* 모달 이미지 */
.detailimagediv {
    position: absolute;
    left: auto;
    margin-top: 0%;
    width: 30%;
    height: 55%;
    border-radius: 25px;
}
.detailimage {
    width: 100%;
    height: 100%;
    border-radius: 25px;
}
/* 모달 콘텐츠들 */
.detailcontents {
    position: absolute;
    right: 15%;
    width: 43%;
    height: 53%;
}
/* 모달 문화재 명 */
.detailtitle {
    margin-top: 0%;
    padding: 1% 1% 1% 1%;
    height: 15%;
    font-size: 40px;
    border: 3px solid #24303F;
    border-radius: 25px;
    background-color:  #24303F;
    color: #E2D6CE;
}
.detailkind {
    position: relative;
    padding-top: 3%;
    left: 0%;
    width: 25%;
    font-size: 20px;
    
}
/* 모달 문화재 상세설명 */
.detaildescription {
    position: relative;
    margin-top: 0%;
    padding: 1% 1% 1% 1%;
    width: 100%;
    height: 55%;
    font-size: 21px;
    overflow: auto; /* Enable scroll if needed */
    border: 5px solid #E2D6CE;
    border-left-color:white;
    border-right-color: white;
    border-radius: 5px;
}
.detailaddress {
    position: relative;
    left: -10%;
    width: 70%;
    margin-top: 2%;
    font-size: 20px;

}
/* The Close Button */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 40px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
</style>
