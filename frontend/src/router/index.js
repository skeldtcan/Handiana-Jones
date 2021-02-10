import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/components/common/Main.vue'
import NoticePage from '@/views/notice/NoticePage.vue'
import Recommend from '@/views/recommend/Recommend.vue'

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/noticepage',
    name: 'NoticePage',
    component: NoticePage,
  },
  {
    name: "Recommend",
    path: "/heritage",
    component: Recommend,
    children: [
      {
        path: "recommend/:user_no",
        name: "RecommendResult",
        component: () => import("@/components/modal/recommend/Recommend.vue")
      }
    ],
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
