<template>
  <v-app>
    <!-- Header.vue를 이용한 헤더 부분 -->
    <Header></Header>
     <!-- Main.vue를 이용한 메인 부분  -->
     <!-- 메인페이지 -->
    <v-main v-if="this.currentUrl=='http://localhost:8080/'">
      <Main/>
    </v-main>
    <!-- 공지사항 페이지 -->
    <v-main v-if="this.currentUrl=='http://localhost:8080/noticepage'">
      <NoticePage/>
    </v-main>
  </v-app>
</template>

<script>
import Header from './components/common/Header';
import Main from './components/common/Main';
import NoticePage from './views/notice/NoticePage';
// auth 부분
// import store from "@/store";

export default {
  name: 'App',
  components: {
    Header,
    Main,
    NoticePage,
  },
  data: () => ({
    login: false,
    currentUrl: '',
  }),
  mounted: function () {
    var link = document.location.href;
    this.currentUrl = link
    console.log(this.currentUrl)
  },
};
</script>

<style>
/* east-sea-Dokdo font import */
@import url('https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap');
/* Gugi font import */
@import url('https://fonts.googleapis.com/css2?family=Gugi&display=swap');
/* Jua font import */
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');
.east-sea-Dokdo {
  font-family: 'East Sea Dokdo', cursive;
}
.gugi {
  font-family: 'Gugi', cursive;
}
.jua {
  font-family: 'Jua', sans-serif;
}

</style>