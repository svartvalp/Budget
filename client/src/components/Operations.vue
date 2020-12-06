<template>
  <v-container>
    <v-dialog v-model="dialog" max-width="500px" >
      <v-card class="card-marged" >
        <v-text-field label="Значение" type="number" v-model="value" ></v-text-field>
        <datepicker v-model="operTime" />
        <v-select class="selectcl" label="Тип" :items="types" v-model="operType" />
        <v-text-field v-model="comment" label="Комментарий"></v-text-field>
        <v-card-actions>
          <v-btn @click="cancel">Назад</v-btn>
          <v-btn @click="saveOper">Сохранить</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  <v-card v-if="authenticated">
    <div v-if="user" class="px-4 pt-4 pb-3" >
      {{user.firstName + "  " + user.lastName}}
      <v-spacer></v-spacer>
      Текущее значение счета : {{currentMoney}}
      <v-subheader>
        <v-checkbox label="Доходы" v-model="income" ></v-checkbox>
        <v-checkbox label="Расходы" v-model="outcome" ></v-checkbox>
        <v-spacer></v-spacer>
        <v-btn @click="filterItems" >Показать</v-btn>
        <v-btn @click="openCreateOper" >Создать</v-btn>
      </v-subheader>
    </div>
    <v-data-table
      :items="shownOperations"
      :headers="headers"></v-data-table>
  </v-card>
  </v-container>
</template>
<script>


import Datepicker from "@/components/datepicker";
import moment from 'moment'
export default {
  components: {Datepicker},
  mounted() {
    console.log(moment())
    this.tryToLoadData()
  },
  methods : {
    tryToLoadData() {
      if(this.user != null ) {
        this.loadCurrentMoney()
        this.loadOperations()
      } else {
        this.$emit('loaduser')
        setTimeout(() => {
          this.tryToLoadData()
        }, 100)
      }
    },
    saveOper() {
      let operation = {
        userId : this.user.id,
        value : this.value,
        type : this.operType === 'Доход' ? 'INCOME' : 'OUTCOME',
        operationTime : new Date(this.operTime),
        comment : this.comment
      }
      this.$http.post(`/monetary`, operation).then(res => {
        this.operations.push(res.data)
        this.dialog = false
        this.value = 0
        this.operType = null
        this.operTime = moment().format("YYYY-MM-DD HH:mm")
        this.filterItems()
        this.loadCurrentMoney()
      })

    },
    cancel() {
      this.dialog = false
    },
    openCreateOper() {
      this.dialog = true
    },
    loadCurrentMoney() {
      this.$http.get(`/monetary/budget/total/user/${this.user.id}`).then(res => {
        this.currentMoney = res.data.currentMoney
      })
    },
    loadOperations() {
      this.$http.get(`/monetary/user/${this.user.id}`).then(res => {
        console.log(res.data)
        this.operations = res.data
        this.filterItems()
        this.showOperations()
      })
    },
    showOperations() {
      this.shownOperations = this.operations.filter(this.filter).map(op => {
        op.localizedType = op.type === 'INCOME' ? 'Доход' : 'Расход'
        return op
      })
    },
    filterItems() {
      console.log(this.income + " " + this.outcome)
      if(this.income) {
        this.filter = (a) => a.type === "INCOME"
      }
      if(this.outcome) {
        this.filter = (a) => a.type === "OUTCOME"
      }
      if(this.income && this.outcome) {
        this.filter = () => true
      }
      if(!this.income && !this.outcome) {
        this.income = true
        this.outcome = true
        this.filter = () => true
      }
      this.showOperations()
    }

  },
  watch : {
    user : function (ouser, nuser) {
      console.log('watch')
      console.log(ouser)
      console.log(nuser)
      if(nuser == null) {
        this.user = ouser
      }
        this.loadCurrentMoney()
      this.loadOperations()
    }
  },
  props : {
  user : Object,
  authenticated : Boolean,
},
  data : () => ({
    currentMoney : null,
    operations : [],
    shownOperations : [],
    filter : () => true,
    income : false,
    outcome : false,
    headers : [
      {
        text : "Значение",
        value : "value"
      },
      {
        text : "Тип",
        value : "localizedType"
      },
      {
        text : "Время операции",
        value : "operationTime"
      },
      {
        text: 'Комментарий',
        value: 'comment'
      }
    ],
    types : [
        "Доход", "Расход"
    ],
    dialog : false,
    operType : '',
    operTime : moment().format("YYYY-MM-DD HH:mm"),
    comment : '',
    value : 0
  })
}
</script>
<style>
.selectcl {
  width: 400px;
}
.card-marged {
  padding: 20px;
}
</style>