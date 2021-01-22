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
            v-model="user.userEmail"
          ></v-text-field>
          <v-text-field
            type="password"
            outlined
            label="비밀번호"
            v-model="user.userPwd"
          ></v-text-field>
            </v-card-text>
            <v-card-actions class="justify-end">
              <v-btn
                color="brownLighten4"
                @click="dialog.value = false"
              ><span class="blue-grey--text text--darken-4 gugi" style="font-size:15px; padding: 10px; ">회원가입</span></v-btn>
              <v-btn
                color="brownLighten4"
                @click="confirm"
              ><span class="blue-grey--text text--darken-4 gugi" style="font-size:15px; padding: 10px; ">로그인</span></v-btn>
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
    name: 'login',
    data(){
      return {
        user:{
          userEmail:null,
          userPwd:null
        },

      }
    },
    computed:{},
    methods:{
      confirm(){
        console.log(this.user.userEmail)
        console.log(this.user.userPwd)
      }
    },

}
</script>

<style>

</style>