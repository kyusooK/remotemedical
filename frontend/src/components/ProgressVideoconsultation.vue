<template>

    <v-card outlined>
        <v-card-title>
            ProgressVideoconsultation
        </v-card-title>

        <v-card-text>
            <String label="VideoId" v-model="value.videoId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="progressVideoconsultation"
            >
                ProgressVideoconsultation
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
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
    name: 'ProgressVideoconsultationCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.videoId = '';
    },
    watch: {
    },
    methods: {
        progressVideoconsultation() {
            this.$emit('progressVideoconsultation', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

