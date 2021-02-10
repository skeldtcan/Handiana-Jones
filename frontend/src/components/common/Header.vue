<template>
  <nav class="mb-16">
        <v-toolbar
        color="navy"
        height="80"
      >
        <div class="d-flex align-center">
          <v-toolbar-title color="navy"><span class="brown--text text--lighten-5 east-sea-Dokdo " style="font-size:48px; cursor:pointer;" @click="moveToMainPage">한디아나존스</span></v-toolbar-title>
        </div>

        <v-spacer></v-spacer>
          <!-- 로그인이 안돼었을시 보이는 배너, vuetify 회원가입, 로그인 모답 -->
          <span v-if="!isLogin" class="mr-2"><Join/></span>
          <span v-if="!isLogin" class="mr-2"><Login/></span>
          <!-- 로그인 상태일때 보이는 배너 -->
          <span v-if="isLogin">
          <span class="brown--text text--lighten-5 jua" style="font-size:16px; padding: 10px; ">{{userInfo.user_name}}님 환영합니다.</span>
          <router-link :to="{ name: 'mypage' }">
            <span class="brown--text text--lighten-5 jua" style="font-size:16px; padding: 10px; ">내 정보</span>
          </router-link>
          <v-btn text
            v-bind="attrs"
            v-on="on"
            @click="logout">
          <span class="brown--text text--lighten-5 jua" style="font-size:16px; padding: 10px; ">로그아웃</span></v-btn>
          </span>
      </v-toolbar>

      <v-img
        :src="require('@/assets/mainImage.jpg')"
        class="md-2"
        height="200"
      />
    <!-- Navbar -->
    <v-toolbar 
    height="70"
    color="grey"
    >
    <!-- 공지사항 버튼 -->
      <v-btn
        color="blue-grey"
        class="ma-2 white--text"
        @click="moveToNoticePage"
      >
        공지사항
        <i class="fas fa-volume-down" style="padding-left: 0.5rem"></i>
      </v-btn>

      <!-- 추천 목록 -->
      <v-btn
        color="blue-grey"
        class="ma-2 white--text"
        @click="moveToRecommend"
      >
        추천 목록
      </v-btn>
    </v-toolbar>
  </nav>
</template>

<script>
import Join from '@/components/modal/auth/Join';
import Login from '@/components/modal/auth/Login';
import { mapState } from "vuex";

export default {
  name: 'header',
  components: {
    Join,
    Login,
  },
  computed: {
    ...mapState(["userInfo", "isLogin"])
  },
  methods: {
    logout: function () {
      this.$store
      .dispatch("LOGOUT")
      .then(() => {
        this.$router.push({ name: "Main" });
      })
      .catch(() => {
        console.log("로그아웃 문제 발생");
      });
    },
    moveToNoticePage: function () {
      this.$router.push({ name: "NoticePage" })
    },
    moveToRecommend() {
      console.log(this.userInfo.user_no)
      this.$router.push({
        name: "RecommendResult",
        params: { user_no: this.userInfo.user_no }
      });
    },
    moveToMainPage: function () {
      this.$router.push({ name: "Main" })
    },
  },
}
</script>

<style>

</style>

