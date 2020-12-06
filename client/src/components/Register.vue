<template>
  <v-container class="fill-height" >
  <v-row align="center" justify="center">
    <v-card width="400px">
      <v-container>
      <v-text-field label="Логин" v-model="login"></v-text-field>
      <v-text-field
          label="Пароль"
          v-model="password"
          :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPass ? 'text' : 'password'"
          @click:append="showPass = !showPass"
      ></v-text-field>
      <v-text-field label="Имя" v-model="firstName"></v-text-field>
      <v-text-field label="Фамилия" v-model="lastName"></v-text-field>
        <v-row align="center" justify="center">
          <v-btn @click="register" >Зарегистрироваться</v-btn>
          <v-btn @click="doLogin" >Войти</v-btn>
        </v-row>
        </v-container>
    </v-card>
  </v-row>
  </v-container>
</template>
<script>
export default {
  methods : {
    doLogin() {
      this.$router.push('/login')
    },
    register() {
      let user = {
        login : this.login,
        password : this.password,
        firstName : this.firstName,
        lastName : this.lastName
      }
      this.$http.post('/register', user).then((res) => {
        this.$emit('register', res.data)
      }).catch(err => {
        if(err.response.data.status === 400) {
          alert(err.response.data.message)
        }
      })
    }
  },
  data : () => ({
    login : '',
    password : '',
    firstName : '',
    lastName : '',
    showPass : false
  })
}
</script>