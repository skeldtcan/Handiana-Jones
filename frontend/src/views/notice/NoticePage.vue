<template>
<div>
    <v-card
    class="mx-auto"
    max-width="1300"
    style="margin-bottom: 30px"
  >
    <v-toolbar
      color="navy"
      dark
    >
      <v-toolbar-title class="jua" style="font-size:20px">공지사항 목록</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </v-toolbar>

    <v-list two-line>
      <v-list-item-group
        v-model="selected"
        active-class="blue--text"
        multiple
      >
        <template v-for="(item, index) in items">
          <v-list-item :key="item.title">
            <template>
              <v-list-item-content>
                <v-list-item-title v-text="item.title"></v-list-item-title>

                <v-list-item-subtitle
                  class="text--primary"
                  v-text="item.headline"
                ></v-list-item-subtitle>

                <v-list-item-subtitle v-text="item.subtitle"></v-list-item-subtitle>
              </v-list-item-content>

              <v-list-item-action>
                <v-list-item-action-text v-text="item.action"></v-list-item-action-text>
              </v-list-item-action>
            </template>
          </v-list-item>
          <v-divider
            v-if="index < items.length - 1"
            :key="index"
          ></v-divider>
        </template>
        <hr style="width:30%; text-align:left; margin-left:10px">
        <!-- pagination -->
        <div class="pagination jua">
          <p style="cursor:pointer">&laquo;</p>
          <div class="paginationsub" v-for="index in 10" :key="index">
            <p style="cursor:pointer">{{index}}</p>
          </div>
          <p style="cursor:pointer">&raquo;</p>
        </div>
      </v-list-item-group>
    </v-list>
  </v-card>
  <NoticeCreate
  style="margin-bottom: 30px"/>
</div>
</template>

<script>
import NoticeCreate from '@/components/modal/notice/NoticeCreate'
export default {
    name:'NoticePage',
    components: {
      NoticeCreate,
    },
    data: () => ({
      selected: [2],
      // 여기에 공지사항 데이터 불러와서 items에 담기.
      items: [
        {
          action: '15 min',
          headline: 'Brunch this weekend?',
          subtitle: `I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
          title: 'Ali Connors',
        },
        {
          action: '2 hr',
          headline: 'Summer BBQ',
          subtitle: `Wish I could come, but I'm out of town this weekend.`,
          title: 'me, Scrott, Jennifer',
        },
        {
          action: '6 hr',
          headline: 'Oui oui',
          subtitle: 'Do you have Paris recommendations? Have you ever been?',
          title: 'Sandra Adams',
        },
        {
          action: '12 hr',
          headline: 'Birthday gift',
          subtitle: 'Have any ideas about what we should get Heidi for her birthday?',
          title: 'Trevor Hansen',
        },
        {
          action: '18hr',
          headline: 'Recipe to try',
          subtitle: 'We should eat this: Grate, Squash, Corn, and tomatillo Tacos.',
          title: 'Britta Holt',
        },
      ],
    }),
}
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