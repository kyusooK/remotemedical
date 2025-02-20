<template>
    <div>
        <String label="예약자명" v-model="value.userName" :editMode="editMode"/>
        <String label="증상" v-model="value.symptoms" :editMode="editMode"/>
        <Date label="예약날짜" v-model="value.reservationDate" :editMode="editMode"/>
        <String label="과거병력" v-model="value.pastHistory" :editMode="editMode"/>
        <ReservationType offline label="예약상태" v-model="value.reservationType" :editMode="editMode" @change="change"/>

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
    name: 'Receiving',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "receivings"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
