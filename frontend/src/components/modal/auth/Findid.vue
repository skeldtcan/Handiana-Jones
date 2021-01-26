<template>
  

    <v-col cols="3">
      <v-dialog
        transition="dialog-bottom-transition"
        max-width="600"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
          text
            color="white"
            v-bind="attrs"
            v-on="on"
          ><span class="blue-grey--text text--darken-4 gugi" style="font-size:20px; padding: 10px; ">아이디찾기</span></v-btn>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar
            class="mb-4"
              color="blueGreyDarken4"
              dark
            ><span class="white--text jua " style="font-size:36px;">아이디찾기</span></v-toolbar>
            <v-card-text>
              <br>
              
          
           <v-text-field
          v-model="user.userName"
          outlined
            label="이름"
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
                @click="findId"
              ><span class="blue-grey--text text--darken-4 gugi" style="font-size:20px; padding: 10px; ">아이디찾기</span></v-btn>
              <v-btn
                text
                @click="dialog.value = false"
              ><span class="blue-grey--text text--darken-4 gugi" style="font-size:20px; padding: 10px; ">창닫기</span></v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>
    </v-col>
  
  
</template>

<script>
export default {
    name: 'Findid',
    data(){
      return {
        user:{
       
          
          userName:null,
          userPhone:null,
        },

      }
    },
    computed:{
      
    },
    methods:{
      findId(){
      
  
        console.log(this.user.userPhone)
        console.log(this.user.userName)
        
      }
    },
}
</script>

<style>

</style>