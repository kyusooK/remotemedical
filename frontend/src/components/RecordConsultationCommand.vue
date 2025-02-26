<template>
    <v-card outlined>
        <v-card-title>
            RecordConsultation
        </v-card-title>

        <v-card-text>
            <String label="ConsultationContent" v-model="value.consultationContent" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="recordConsultation"
            >
                RecordConsultation
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'RecordConsultationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.consultationContent = '';
        },
        watch: {
        },
        methods: {
            recordConsultation() {
                this.$emit('recordConsultation', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

