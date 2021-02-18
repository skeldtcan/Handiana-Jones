<template>
  <div>
    <v-card class="mx-auto" width="80%" style="margin-bottom: 5%; margin-top: 5%;">
      <v-toolbar color="navy" dark>
        <v-toolbar-title class="jua" style="font-size:20px"
          >공지사항 목록</v-toolbar-title
        >
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" persistent max-width="300px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn class="" v-bind="attrs" v-on="on" icon>
              <v-icon> mdi-magnify</v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="검색할 번호를 입력하세요."
                      v-model="noticeNumber"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-btn color="grey" @click="searchNotice" class="jua" style="font-size:16px; float: right;">
                검색
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>

      <template>
        <v-expansion-panels focusable>
          <v-expansion-panel v-for="(item, index) in items" :key="index">
            <!-- 제목 -->
            <v-expansion-panel-header class="jua brown1" style="font-size:19px;">{{
              '[' + item.notice_no + '] ' + item.notice_title
            }}</v-expansion-panel-header>

            <!-- 내용 -->
            <v-expansion-panel-content>
              <tiptap-vuetify
                v-model="item.notice_content"
                :extensions="extensions"
                >{{ item.notice_content }}
                <!-- <div slot="toolbar"></div> -->
              </tiptap-vuetify>
              <v-btn @click="modifyNotice(index)" class="jua brown4 white--text" style="font-size: 19px; float: right; margin-left: 10px">수정</v-btn>
              <!-- 작성자 -->
              <v-list-item-action-text>
                <!-- {{ item.user_no }} -->
              </v-list-item-action-text>
              <v-btn @click="deleteNotice(index)" value="index" class="jua grey white--text" style="font-size: 19px; float: right;">삭제</v-btn>
            </v-expansion-panel-content>

          </v-expansion-panel>
        </v-expansion-panels>
        <!-- <v-divider v-if="index < items.length - 1" :key="index"></v-divider> -->
      </template>

      <v-list two-line>
        <v-list-item-group
          v-model="selected"
          active-class="blue--text"
          multiple
        >
          <hr style="width:70%; text-align:left; margin-left:10px" />
          <!-- pagination -->
          <div class="pagination jua">
            <p style="cursor:pointer">&laquo;</p>
            <div class="paginationsub" v-for="index in 10" :key="index">
              <p style="cursor:pointer">{{ index }}</p>
            </div>
            <p style="cursor:pointer">&raquo;</p>
          </div>
        </v-list-item-group>
      </v-list>
    </v-card>
    <NoticeCreate style="margin-bottom: 10%" />
  </div>
</template>

<script>
import NoticeCreate from '@/components/modal/notice/NoticeCreate';
import {
  listNoticepage,
  modifyNoticepage,
  deleteNoticepage,
} from '@/api/notice.js';

import {
  TiptapVuetify,
  Heading,
  Bold,
  Italic,
  Strike,
  Underline,
  Code,
  Paragraph,
  BulletList,
  OrderedList,
  ListItem,
  Link,
  Blockquote,
  HardBreak,
  HorizontalRule,
  History,
} from 'tiptap-vuetify';
import { mapState } from 'vuex';

export default {
  name: 'NoticePage',
  components: {
    NoticeCreate,
    TiptapVuetify,
  },

  created() {
    listNoticepage((response) => {
      console.log(this.items);
      this.items = response.data;
    });
  },

  computed: {
    ...mapState(['userInfo', 'isLogin']),
  },

  data() {
    return {
      notice: {
        notice_no: '',
        user_name: '',
        notice_title: '',
        notice_content: '',
      },
      dialog: false,
      // selected: [2],

      extensions: [
        History,
        Blockquote,
        Link,
        Underline,
        Strike,
        Italic,
        ListItem,
        BulletList,
        OrderedList,
        [
          Heading,
          {
            options: {
              levels: [1, 2, 3],
            },
          },
        ],
        Bold,
        Code,
        HorizontalRule,
        Paragraph,
        HardBreak,
      ],

      // 여기에 공지사항 데이터 불러와서 items에 담기.
      items: [
        {
          notice_content: '',
          notice_no: '',
          notice_title: '',
          user_name: '',
        },
      ],
    };
  },
  methods: {
    searchNotice() {
      this.dialog = false;
      console.log();
    },
    modifyNotice(index) {
      modifyNoticepage(this.items[index], (response) => {
        this.items = response.data;
      });
      window.location.reload();
    },
    deleteNotice(index) {
      deleteNoticepage(this.items[index].notice_no, (response) => {
        this.items = response.data;
      });
      window.location.reload();
    },
  },
};
</script>

<style>
.pagination {
  display: inline-flex;
  padding-left: 15px;
  padding-top: 15px;
}
.paginationsub {
  display: inline-block;
  padding-left: 10px;
  padding-right: 10px;
}

.paginationsub p {
  color: black;
  float: left;
  text-decoration: none;
}
</style>
