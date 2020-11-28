<template>
  <div class="js-datepicker">
    <label class="flatpickr-lbl">
      Дата
      <input
          type="text"
          ref="datepicker"
          class="form-control flatpickr-input"
          :value="value"
          :placeholder="placeholder" />
    </label>
  </div>
</template>

<style>
.js-datepicker .flatpickr-input {
  background-color: #fff;
  display: flex;
  flex-direction: row;
}
.flatpickr-lbl {
  padding-bottom: 10px;
}
.flatpickr-input {
  margin-top: 10px;
  background-color: blanchedalmond;
}
</style>

<script>
// Плагин календаря, локализация, стили
import flatpickr from 'flatpickr'
import { Russian } from 'flatpickr/dist/l10n/ru.js'
import 'flatpickr/dist/flatpickr.css'

export default {
  name: 'Datepicker',
  props: {
    // Отображаемая дата
    value: {
      type: String,
      required: true
    },

    // Подсказка для поля
    placeholder: {
      type: String,
      default: 'Выберите дату'
    },

  },
  data: () => ({
    // Экземпляр плагина
    fp: null
  }),
  watch: {
    // Обновление даты сверху должно обновлять календарь
    value: 'updateDatepicker'
  },
  mounted() {
    this.initCalendar()
  },
  methods: {
    // Обновление даты при изменении входных данных
    // Оригинальное поле скрывается календарём и нужно обновлять
    updateDatepicker() {
      if (this.fp) {
        this.fp.setDate(this.value)
      }
    },

    // Инициализация плагина календаря
    initCalendar() {
      this.fp = flatpickr(this.$refs.datepicker, {
        locale: Russian,
        enableTime: true,
        onChange: (selectedDates, dateStr) => {
          this.$emit('input', dateStr)
        }
      })
    }
  }
}
</script>
