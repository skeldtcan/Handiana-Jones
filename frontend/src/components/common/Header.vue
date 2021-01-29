<template>
  <nav class="mb-16">
        <v-toolbar
        app
        color="navy"
        height="80"
      >
        <div class="d-flex align-center">
          <v-toolbar-title color="navy"><span class="brown--text text--lighten-5 east-sea-Dokdo " style="font-size:48px;">한디아나존스</span></v-toolbar-title>
        </div>

        <v-spacer></v-spacer>

          <!-- 로그인이 안돼었을시 보이는 배너, vuetify 회원가입, 로그인 모답 -->
          <span v-if="!isLogin" class="mr-2"><Join/></span>
          <span v-if="!isLogin" class="mr-2"><Login/></span>
          <!-- 로그인 상태일때 보이는 배너 -->
          <span v-if="isLogin">
            <v-btn text
              v-bind="attrs"
              v-on="on"
              @click="logout"
            ><span class="brown--text text--lighten-5 jua" style="font-size:16px; padding: 10px; ">로그아웃</span></v-btn>
          </span>
          
      </v-toolbar>

      <v-img
        :src="require('@/assets/mainImage.jpg')"
        class="md-2"
        height="200"
      />
    <v-toolbar app
    height="70"
    color="grey"
    >
    </v-toolbar>
  </nav>
</template>

<script>
import Join from '@/components/modal/auth/Join';
import Login from '@/components/modal/auth/Login';

export default {
  name: 'Header',
  components: {
    Join,
    Login,
  },
  data: function () {
    return {
      isLogin: false,
    }
  },
  methods: {
    logout: function () {
      localStorage.removeItem("access-token")
      this.isLogin = false
      
    }
  },
  mounted: function () {
    let token = localStorage.getItem("access-token");
    if (token) {
      this.isLogin = true
    }
  }
}
</script>

<style>

</style>

