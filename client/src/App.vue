<template>
  <v-app>
    <v-app-bar app dark color="secondary">
      Доходник
      <v-spacer></v-spacer>
      <v-btn v-if="isAuthenticated" @click="doLogout"  light color="primary" >Выход</v-btn>
    </v-app-bar>
    <v-main>
        <router-view @loaduser="loadUser"  @login="doLogin" @register="doRegister"  :user="user" :authenticated="isAuthenticated" />
    </v-main>
  </v-app>
</template>
<script>
export default {
  data: () => ({
    isAuthenticated: false,
    user: null
  }),
  methods : {
    doLogout() {
      this.$router.push('/login')
      this.isAuthenticated = false
      this.user = null
    },
    doLogin() {
      this.isAuthenticated = true
    },
    doRegister(user) {
      this.isAuthenticated = true
      this.user = user
      this.$router.push('/')
    },
    loadUser(){
      this.$http.get('/user/info').then((res) => {
        this.user = res.data
        this.isAuthenticated = true
      }).catch(() => {
        this.isAuthenticated = false
        this.$router.push('/login')
      })
    }
  },
  mounted() {
    this.loadUser()
  }
}
</script>
<style lang="scss">
</style>
