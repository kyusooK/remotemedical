<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="progressVideoconsultationDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>화상 상담 진행
                </v-btn>
                <v-dialog v-model="progressVideoconsultationDialog" width="500">
                    <ProgressVideoconsultation
                        @closeDialog="progressVideoconsultationDialog = false"
                        @progressVideoconsultation="progressVideoconsultation"
                    ></ProgressVideoconsultation>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="recordConsultationDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>진료 기록
                </v-btn>
                <v-dialog v-model="recordConsultationDialog" width="500">
                    <RecordConsultation
                        @closeDialog="recordConsultationDialog = false"
                        @recordConsultation="recordConsultation"
                    ></RecordConsultation>
                </v-dialog>
            </div>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>환자명</th>
                        <th>환자증상</th>
                        <th>환자 과거 병력</th>
                        <th>진료내용</th>
                        <th>화상채팅ID</th>
                        <th>진료 상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" :key="val" @click="changeSelectedRow(val)" :style="val === selectedRow ? 'background-color: #f0f3ff;':''">
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="환자명">{{ val.patientName }}</td>
                            <td class="whitespace-nowrap" label="환자증상">{{ val.patientSymptoms }}</td>
                            <td class="whitespace-nowrap" label="환자 과거 병력">{{ val.pastHistory }}</td>
                            <td class="whitespace-nowrap" label="진료내용">{{ val.consultationContent }}</td>
                            <td class="whitespace-nowrap" label="화상채팅ID">{{ val.videoId }}</td>
                            <td class="whitespace-nowrap" label="진료 상태">{{ val.consultationStatus }}</td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Consultation 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Consultation :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Consultation 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <String label="환자명" v-model="selectedRow.patientName" :editMode="true"/>
                            <String label="환자증상" v-model="selectedRow.patientSymptoms" :editMode="true"/>
                            <String label="환자 과거 병력" v-model="selectedRow.pastHistory" :editMode="true"/>
                            <String label="진료내용" v-model="selectedRow.consultationContent" :editMode="true"/>
                            <String label="화상채팅ID" v-model="selectedRow.videoId" :editMode="true"/>
                            <ConsultationStatus offline label="진료 상태" v-model="selectedRow.consultationStatus" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'consultationGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'consultations',
        progressVideoconsultationDialog: false,
        recordConsultationDialog: false,
    }),
    watch: {
    },
    methods:{
        progressVideoconsultation(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "progressVideoconsultation", params)
                this.$EventBus.$emit('show-success','progress videoconsultation 성공적으로 처리되었습니다.')
                this.progressVideoconsultationDialog = false
            }catch(e){
                console.log(e)
            }
            
        },
        recordConsultation(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "recordConsultation", params)
                this.$EventBus.$emit('show-success','record consultation 성공적으로 처리되었습니다.')
                this.recordConsultationDialog = false
            }catch(e){
                console.log(e)
            }
            
        },
    }
}

</script>