<template>
  <v-row justify="">

    <v-col cols="auto">
      <v-dialog
        transition="dialog-bottom-transition"
        max-width="600"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn text
            v-bind="attrs"
            v-on="on"
          ><span class="brown--text text--lighten-5 jua" style="font-size:16px; padding: 10px; ">회원가입</span></v-btn>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar
            class="mb-4"
              color="navy"
              dark
            ><span class="brown--text text--lighten-5 jua" style="font-size:24px;">회원가입</span></v-toolbar>
            <v-card-text>
              <br>
              <v-text-field
              v-model="user.user_id"
              outlined
            label="이메일"
          ></v-text-field>
          <v-text-field
          type="password"
          outlined
          v-model="user.user_password"
            label="비밀번호"
          ></v-text-field>
          <v-text-field
          type="password"
          outlined
          v-model="passcon"
            label="비밀번호확인"
          ></v-text-field>
           <v-text-field
          v-model="user.user_name"
          outlined
            label="이름"
          ></v-text-field>
            
          <v-text-field
          v-model="user.user_phone"
          outlined
            label="연락처"
          ></v-text-field>
            </v-card-text>
            <v-card-actions class="justify-end">
              <v-btn
                color="brown2"
                @click="create"
              ><span class="blue-grey--text text--darken-4 jua" style="font-size:18px; padding: 10px; ">가입하기</span></v-btn>
              <v-btn
                text
                @click="dialog.value = false"
              ><span class="blue-grey--text text--darken-4 jua" style="font-size:18px; padding: 10px; ">창닫기</span></v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>
    </v-col>
  </v-row>
  
</template>

<script>
import { join } from "@/api/user.js";

export default {
    name: 'join',
    data(){
      return {
        user:{
          user_id:null,
          user_password:null, 
          user_name:null,
          user_phone:null,
        },
        passcon:'',

      }
    },
    computed:{
      
    },
    methods:{
      create() {
        if (this.passcon === this.user.user_password) {
          join(
            this.user, 
          (response)=>{
            console.log("회원가입성공", response)
          },
          (error)=>{
            if (error.response.status === 409) {
              alert("사용자 이메일이 중복됐습니다. 다른 이메일 아이디를 사용해주세요")
            }
            if (error.response.status === 500) {
              alert("회원가입에 실패했습니다.")
            }
            })
        } else {
          alert("비밀번호와 비밀번호 확인이 일치하지 않습니다. 다시 시도해 주세요.")
        }
      }
    },
}
</script>

<style>

</style>