<template>
  <div>
        <v-app-bar
        app
        color="blueGreyDarken4"
      >
        <div class="d-flex align-center">
          <v-toolbar-title color="blueGreyDarken4"><span class="white--text east-sea-Dokdo " style="font-size:48px;">한디아나존스</span></v-toolbar-title>
        </div>

        <v-spacer></v-spacer>

          <!-- 로그인이 안돼었을시 보이는 배너, vuetify 회원가입, 로그인 모답 -->
          <span v-if="!isLogin" class="mr-2"><Join/></span>
          <span v-if="!isLogin" class="mr-2"><Login/></span>
          <!--  -->
          <span v-if="isLogin">
            <v-btn
              color="brownLighten4"
              v-bind="attrs"
              v-on="on"
              @click="logout"
            ><span class="blue-grey--text text--darken-4 gugi" style="font-size:20px; padding: 10px; ">로그아웃</span></v-btn>
          </span>
          
        
      </v-app-bar>
  </div>
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

