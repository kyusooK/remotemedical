<template>
    <div class="custom-text-field">
      <v-text-field
        :value="value"
        @input="$emit('input', $event)"
        v-bind="$attrs"
        v-on="$listeners"
      ></v-text-field>
    </div>
  </template>
  <script>
  export default {
    name: 'CustomTextField',
    inheritAttrs: false,
    props: {
      value: {
        type: [String, Number],
        default: ''
      }
    }
  }
  </script>