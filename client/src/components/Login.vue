<template>
  <v-container class="fill-height">
    <v-row align="center" justify="center" >
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
          <v-row justify="center" align="center">
            <v-btn @click="doLogin">Войти</v-btn>
            <v-btn @click="doRegister" >Зарегистрироваться</v-btn>
          </v-row>
        </v-container>
      </v-card>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data : () => ({
    login : '',
    password : '',
    showPass: false
  }),
  methods : {
    doRegister() {
      this.$router.push('/register')
    },
    doLogin() {
      this.$http.post(`/login?username=${this.login}&password=${this.password}`).then(() => {
        this.$emit('login')
        this.$router.push('/')
      })
    }
  },
}
</script>