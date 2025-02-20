<template>
    <div>
        <String label="환자명" v-model="value.patientName" :editMode="editMode"/>
        <String label="환자증상" v-model="value.patientSymptoms" :editMode="editMode"/>
        <String label="환자 과거 병력" v-model="value.pastHistory" :editMode="editMode"/>
        <String label="진료내용" v-model="value.consultationContent" :editMode="editMode"/>
        <String label="화상채팅ID" v-model="value.videoId" :editMode="editMode"/>
        <ConsultationStatus offline label="진료 상태" v-model="value.consultationStatus" :editMode="editMode" @change="change"/>

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
    name: 'Consultation',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "consultations"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
