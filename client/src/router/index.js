import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Operations from "@/components/Operations";

Vue.use(VueRouter)

const routes = [
  {
    path : '/login',
    component : Login
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '',
    component: Operations
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
