<template>
    <div>
        <Date label="ConsultationDate" v-model="value.consultationDate" :editMode="editMode"/>
        <String label="Symptoms" v-model="value.symptoms" :editMode="editMode"/>
        <String label="결제ID" v-model="value.paymentId" :editMode="editMode"/>
        <String label="청구비용" v-model="value.consultationExpense" :editMode="editMode"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Insurance',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "insurances"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
