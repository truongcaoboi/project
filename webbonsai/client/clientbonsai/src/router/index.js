import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/HomePage'
import TreeDetail from '@/components/tree/TreeDetail'
import Cart from '@/components/cart/Cart'
import Login from '@/components/common/Login'
import SignUp from '@/components/common/SignUp'
import UserDetail from '@/components/user/UserDetail'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage,
    },
    {
      path: '/detail/:id',
      name: 'TreeDetail',
      component: TreeDetail,
    },
    {
      path: '/cart',
      name: 'Cart',
      component: Cart,
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
    },
    {
      path: '/signup',
      name: 'SignUp',
      component: SignUp,
    },
    {
      path: '/userDetail',
      name: 'UserDetail',
      component: UserDetail,
    },
  ]
})
