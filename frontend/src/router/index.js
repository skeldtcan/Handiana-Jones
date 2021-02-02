import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/components/common/Main.vue'
import NoticePage from '@/views/notice/NoticePage.vue'


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


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
