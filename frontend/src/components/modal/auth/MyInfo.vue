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
          >
          <span class="brown--text text--lighten-5 jua" style="font-size:18px; padding: 5px;">{{ userInfo.user_name }}님</span>
          </v-btn>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar
              color="navy"
              class="mb-4"
              dark
            ><span class="brown--text text--lighten-5 jua" style="font-size:24px;">회원 정보</span>
            <v-spacer/>
              <v-btn
                text
                @click="dialog.value = false"
              >
              <span class="brown1--text jua" style="font-size:14px; padding: 10px; ">x</span>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <br>
                <v-row class="mx-0">
                    <v-col cols="2">
                    아이디
                    </v-col>
                    <v-text-field
                    v-model="userid" cols="8">
                    </v-text-field>
                </v-row>
                <v-row class="mx-0">
                    <v-col cols="2">
                    이름
                    </v-col>
                    <v-text-field v-model="username" cols="8">
                    {{ userInfo.user_name }}
                    </v-text-field>
                </v-row>
                <v-row class="mx-0">
                    <v-col cols="2">
                    비밀번호
                    </v-col>
                    <v-text-field v-model="userpwd" cols="8">
                    {{ userInfo.user_password }}
                    </v-text-field>
                </v-row>
                <v-row class="mx-0">
                    <v-col cols="2">
                    연락처
                    </v-col>
                    <v-text-field v-model="userphone" cols="8">
                    {{ userInfo.user_phone }}
                    </v-text-field>
                </v-row>
            </v-card-text>
            <v-card-actions class="justify-end">
              <v-btn
                color="brown1"
                @click="deleteUser"
              ><span class="blue-grey--text text--darken-4 jua" style="font-size:16px; padding: 10px; ">회원 탈퇴</span>
              </v-btn>
            <v-btn
                color="brown2"
                @click="modifyUser"
              ><span class="blue-grey--text text--darken-4 jua" style="font-size:16px; padding: 10px; ">정보 수정</span>
              </v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>
    </v-col>
 </v-row>
  
</template>

<script>
import { findById, modifyInfo, deleteInfo } from "@/api/user.js";
import { mapState } from "vuex";

export default {
    name: 'myInfo',
    data(){
      return {
        user: {},
        userid: null,
        username: null,
        userpwd: null,
        userphone: null,
      };
    },
    computed: {
        ...mapState(["userInfo", "isLogin"])
    },
    created() {
        this.userid = this.userInfo.user_id;
        this.username = this.userInfo.user_name;
        this.userphone = this.userInfo.user_phone;
        findById(
          this.userInfo.user_id,
          (response) => {
            if (response.data.message === "success") {
                console.log('성공');
            } else {
              console.log('실패');
            }
          },
          (error) => {
            console.log(error);
          }
        );
    },
    methods: {
        modifyUser(){
            this.userInfo.user_id = this.userid;
            this.userInfo.user_name = this.username;
            this.userInfo.user_password = this.userpwd;
            this.userInfo.user_phone = this.userphone;
            modifyInfo(
                this.userInfo,
                (response) => {
                    if (response.data.message === "success") {
                        console.log('성공');
                    } else {
                    console.log('실패');
                    }
                },
                (error) => {
                    console.log(error);
            })
        },
        deleteUser(){
            deleteInfo(
                this.userInfo.user_id,
                (response) => {
                    if (response.data.message === "success") {
                        console.log('성공');
                    } else {
                    console.log('실패');
                    }
                },
                (error) => {
                    console.log(error);
            })
        },
    },
}
</script>

<style>

</style>