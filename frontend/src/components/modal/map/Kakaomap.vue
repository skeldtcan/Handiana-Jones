<template>
	<div class="container">
		<!-- search bar -->
		<div class="search">
			<input type="text" class="searchTerm" placeholder="문화재 또는 지역을 검색해 주세요" v-model="keyword">
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
		VueDaumMap
	},
	data: () => ({
		// 카카오맵을 위한 data instances
		appKey: '2e4b87e7a16709ce4966d35d9caaad46',
		center: { lat: 36.2683, lng: 127.6358 },
		level: 14,
		mapTypeId: VueDaumMap.MapTypeId.NORMAL,
		libraries: ['services', 'clusterer', 'drawing'],
		map: null,
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
			this.setMarkerCluster(addressData)
		// initMap 함수 종료 선
		},
		// 마커 클러스터러를 생성.
		setMarkerCluster(data) {
			let clusterer = new window.kakao.maps.MarkerClusterer({
				map: this.map,
				averageCenter: true,
				minLevel: 10,
			});
			let markers = data.positions.map(
				(position) => {
					return new window.kakao.maps.Marker({
						position: new window.kakao.maps.LatLng(position.lat, position.lng),
				});
			});
			clusterer.addMarkers(markers)
		},
	// 키워드로 지역 검색
		keywordMarker: function () {
			// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
			var infowindow = new kakao.maps.InfoWindow({zIndex:1});

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
			this.keyword = ''

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

			// 지도에 마커를 표시하는 함수입니다
			function displayMarker(place) {
				
				// 마커를 생성하고 지도에 표시합니다
				var marker = new kakao.maps.Marker({
					map: map,
					position: new kakao.maps.LatLng(place.y, place.x) 
				});

				// 마커에 클릭이벤트를 등록합니다
				kakao.maps.event.addListener(marker, 'click', function() {
					// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
					infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
					infowindow.open(map, marker);
				});
			}
		// methods 종료부분 
		},
	},
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
</style>