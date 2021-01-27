<template>
  <v-row justify="">
    <v-col cols="auto">
      <v-dialog
        transition="dialog-bottom-transition"
        max-width="600"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="brownLighten4"
            v-bind="attrs"
            v-on="on"
           
          ><span class="blue-grey--text text--darken-4 gugi" style="font-size:20px; padding: 10px; ">로그인</span></v-btn>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar
              color="blueGreyDarken4"
              class="mb-4"
              dark
            ><span class="white--text jua" style="font-size:30px;">로그인</span></v-toolbar>
            <v-card-text>
              <br>
               <v-text-field
            label="이메일"
            outlined
            v-model="user.user_id"
          ></v-text-field>
          <v-text-field
            type="password"
            outlined
            label="비밀번호"
            v-model="user.user_password"
          ></v-text-field>
            </v-card-text>
            <v-card-actions class="justify-end">
              <Findid/>
              
              <Findpwd/>
            </v-card-actions>
            <v-card-actions class="justify-end">
              <Join/>
              
              <v-btn
                color="brownLighten4"
                @click="confirm"
              ><span class="blue-grey--text text--darken-4 gugi" style="font-size:20px; padding: 10px; ">로그인</span></v-btn>
              <v-btn
                text
                @click="dialog.value = false"
              ><span class="blue-grey--text text--darken-4 gugi" style="font-size:20px; padding: 10px; ">창닫기</span></v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>
    </v-col>
 </v-row>
  
</template>

<script>
import { login } from "@/api/user.js";
import Join from '@/components/modal/auth/Join';
import Findpwd from '@/components/modal/auth/Findpwd';
import Findid from '@/components/modal/auth/Findid';


export default {
    name: 'login',
    data(){
      return {
        user:{
          user_id:null,
          user_password:null
        },

      }
    },
    components:{
      Join,
    Findpwd,
    Findid,
    },
    computed:{},
    methods:{
      confirm() {
        localStorage.setItem("access-token", "");
        login(
        this.user,
        (response) => {
          console.log("최종로그인확인", response.data.message)
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            this.$store.commit("setIsLogined", true);
            localStorage.setItem("access-token", token)
            this.$store.dispatch("GET_MEMBER_INFO", token);
            // this.$router.push("/");
            // 페이지 리렌더링
            location.reload();
          } else {
            console.log('로그인 실패')
            this.isLoginError = true;
          }
        },
        (error) => {
          console.error(error);
          alert("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
      );
      }
    },

}
</script>

<style>

</style>