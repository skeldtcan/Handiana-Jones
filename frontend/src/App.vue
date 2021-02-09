<template>
  <v-app>
    <!-- Header.vue를 이용한 헤더 부분 -->
    <Header></Header>
    <!-- 헤더 이하 내용 -->
    <router-view />
  </v-app>
</template>

<script>
import Header from './components/common/Header';
// auth 부분
// import store from "@/store";

export default {
  name: 'App',
  components: {
    Header,
  },
  data: () => ({
    login: false,

  }),
  mounted: function () {

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
/* Black Han Sans */
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap');
.east-sea-Dokdo {
  font-family: 'East Sea Dokdo', cursive;
}
.gugi {
  font-family: 'Gugi', cursive;
}
.jua {
  font-family: 'Jua', sans-serif;
}
.han {
  font-family: 'Black Han Sans', sans-serif;
}
</style>