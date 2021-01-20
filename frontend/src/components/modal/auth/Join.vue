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
          ><span class="blue-grey--text text--darken-4 gugi" style="font-size:20px; padding: 10px; ">회원가입</span></v-btn>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar
            class="mb-4"
              color="blueGreyDarken4"
              dark
            ><span class="white--text east-sea-Dokdo " style="font-size:48px;">회원가입</span></v-toolbar>
            <v-card-text>
              <br>
              <v-text-field
              v-model="user.userEmail"
              outlined
            label="이메일"
          ></v-text-field>
          <v-text-field
          type="password"
          outlined
          v-model="user.userPwd"
            label="비밀번호"
          ></v-text-field>
          <v-text-field
          type="password"
          outlined
          v-model="user.userPwdCon"
            label="비밀번호확인"
          ></v-text-field>
          <v-text-field
          v-model="user.userPhone"
          outlined
            label="연락처"
          ></v-text-field>
            </v-card-text>
            <v-card-actions class="justify-end">
              <v-btn
                color="brownLighten4"
                @click="create"
              ><span class="blue-grey--text text--darken-4 gugi" style="font-size:15px; padding: 10px; ">회원가입</span></v-btn>
              <v-btn
                text
                @click="dialog.value = false"
              ><span class="blue-grey--text text--darken-4 gugi" style="font-size:15px; padding: 10px; ">창닫기</span></v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>
    </v-col>
  </v-row>
  
</template>

<script>
export default {
    name: 'join',
    data(){
      return {
        user:{
          userEmail:null,
          userPwd:null,
          userPwdCon:null,
          userPhone:null,
        },

      }
    },
    computed:{
      
    },
    methods:{
      create(){
        console.log(this.user.userEmail)
        console.log(this.user.userPwd)
        console.log(this.user.userPwdCon)
        console.log(this.user.userPhone)
        if (this.user.userPwd===this.user.userPwdCon){
          console.log('비밀번호가 옳습니다')
        } else {
          console.log('비밀번호가 틀립니다')
        }
      }
    },
}
</script>

<style>

</style>