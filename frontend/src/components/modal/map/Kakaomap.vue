<template>
    <div class="container">
        <!-- search bar -->
        <div class="search">
            <input type="text" class="searchTerm" placeholder="문화재 또는 지역을 검색해 주세요" v-model="keyword" @keypress.enter="keywordMarker()">
            <button type="" class="searchButton" @click="keywordMarker()">
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

    </div>
</template>

<script>
import VueDaumMap from 'vue-daum-map';
export default {
    name: 'Kakaomap',
    components: {
        VueDaumMap,
    },
    data: () => ({
        // 카카오맵을 위한 data instances
        appKey: '2e4b87e7a16709ce4966d35d9caaad46',
        center: { lat: 36.2683, lng: 127.6358 }, // 지도의 중심 좌표
        level: 14, 
        mapTypeId: VueDaumMap.MapTypeId.NORMAL,
        libraries: ['services', 'clusterer', 'drawing'],
        map: null,
        // keyword 검색을 위한 data instances
        keyword: ''
    }),
    methods: {
        // 페이지에 최초 맵을 생성할 때 DB에 담긴 모든 문화유적의 마커를 깔고 클러스트로 표현하며 시작한다.
        onLoad() {
            var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
                    center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
                    level : 14 // 지도의 확대 레벨
                });
            this.map = map;
            // 클러스터 생성을 위한 json 데이터 받아오기
            const addressData = require('@/assets/clustertest.json');
            // cluster 생성 methods 실행
            this.setMarkerCluster(addressData, map)
        },
        // 마커 클러스터러를 생성.
        setMarkerCluster(data, map) {
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
            let markers = data.positions.map(
                (positionSingle) => {
                    // // 마커 이미지를 커스텀하기 위한 코드
                    // var imageSrc = require('@/assets/exampleImg.jpg'), // 마커이미지의 주소입니다    
                    //     imageSize = new kakao.maps.Size(50, 40), // 마커이미지의 크기입니다
                    //     imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
                    // // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
                    // var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

                // // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                //     var content = '<div class="customoverlay">' +
                //     '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
                //     '    <span class="title">구의야구공원</span>' +
                //     '  </a>' +
                //     '</div>';
                    
                //     // 커스텀 오버레이가 표시될 위치입니다 
                //     var position = new kakao.maps.LatLng(positionSingle.lat, positionSingle.lng);  

                //     // 커스텀 오버레이를 생성합니다
                //     var customOverlay = new kakao.maps.CustomOverlay({
                //         map: map,
                //         position: position,
                //         content: content,
                //         yAnchor: 1 
                //     });
                //     // map에 커스텀오버레이 표시
                //     customOverlay.setMap(map);

                    // markers에 담길 position과 image 값들을 반환합니다.
                    return new window.kakao.maps.Marker({
                        // marker에 담고싶은 정보들 data 이용해서 이 부분부터 추가.(중요)
                        position: new window.kakao.maps.LatLng(positionSingle.lat, positionSingle.lng),
                        // image: markerImage,
                    });
            });
            
            // 마커에 마우스오버 등록을 위한 코드 시작.
            for (var i = 0; i < markers.length; i ++) {
                    var marker = markers[i]
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
                    var content4 = '<div class="infoTitle"><text class="jua">문화재명</text></div>'
    
                    var infowindow = new kakao.maps.InfoWindow({
                        content: content1+content2+content3+content4 // 인포윈도우에 표시할 내용
                    });
                    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
                    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
                    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
                    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
                    // 마커에 클릭이벤트를 등록합니다
                    kakao.maps.event.addListener(marker, 'click', makeClickListener(map, marker, infowindow));

                    
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
            function makeClickListener(map, marker, infowindow) {
                return function () {
                    infowindow.setContent('<div style="padding:5px;font-size:12px;">click succeed</div>');
                    infowindow.open(map, marker);
                }
            }

            // clusterer에 마커들을 추가.(중요)
            clusterer.addMarkers(markers)
        },
        
        // keyword 검색을 위한 Method
        keywordMarker: function () {
            var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                mapOption = {
                    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
                    level: 3 // 지도의 확대 레벨
                };  

            // 지도를 생성합니다    
            var map = new kakao.maps.Map(mapContainer, mapOption); 

            // 장소 검색 객체를 생성합니다
            var ps = new kakao.maps.services.Places();

            // 키워드로 장소를 검색합니다
            ps.keywordSearch(this.keyword, placesSearchCB); 

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
                    position: new kakao.maps.LatLng(place.y, place.x)
                });
                // 마커에 마우스오버 등록을 위한 코드 시작.
                var iwContent = '<div style="padding:5px;">Hello World!</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                // 인포윈도우를 생성합니다
                var infowindow = new kakao.maps.InfoWindow({
                    content : iwContent
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
                    infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                    infowindow.open(map, marker);
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
    width:60rem; height:40rem; margin-left:auto; margin-right:auto;
}

/* Info Window */
.infoImg {
    width:20rem; height:10rem;
    padding: 1rem 1rem 0rem 1rem;
}
.infoTitle {
    padding: 1rem;
    font-size: 30px;
}
</style>
