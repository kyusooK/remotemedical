<template>
    <v-app id="inspire">
        <div>
            <v-app-bar color="primary" app clipped-left flat>
                <v-toolbar-title>
                    <span class="second-word font uppercase"
                        style="font-weight:700;"
                    >
                        <v-app-bar-nav-icon
                            @click="openSideBar()"
                            style="z-index:1;
                            height:56px;
                            width:30px;
                            margin-right:10px;
                            font-weight:300;
                            font-size:55px;"
                        >
                            <div style="line-height:100%;">≡</div>
                        </v-app-bar-nav-icon>
                    </span>
                </v-toolbar-title>
                <span v-if="urlPath!=null" 
                    class="mdi mdi-home" 
                    key="" 
                    to="/" 
                    @click="goHome()"
                    style="margin-left:10px; font-size:20px; cursor:pointer;"
		        ></span> 
                <v-spacer></v-spacer>

            </v-app-bar>

            <v-navigation-drawer app clipped flat v-model="sideBar">
                <v-list>
                    <v-list-item
                        class="px-2"
                        key="receivings"
                        to="/receivings/receivings"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        접수
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="consultations"
                        to="/consultations/consultations"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        진료
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="insurances"
                        to="/insurances/insurances"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        보험
                    </v-list-item>

                    <v-list-item
                        class="px-2"
                        key=""
                        to="/chats/chatRooms"
                        @click="startChat()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        진료 채팅
                    </v-list-item>


                </v-list>
            </v-navigation-drawer>
        </div>
        <v-main v-if="openChatRoom">
            <chat-app>
                <chat-chat-room-cards/>
            </chat-app>
        </v-main>
        <v-main v-else>
            <v-container style="padding:0px;" v-if="urlPath" fluid>
                <router-view></router-view>
            </v-container>
            <v-container style="padding:0px;" v-else fluid>
                <div style="width:100%; margin:0px 0px 20px 0px; position: relative;">
                    <v-img style="width:100%; height:300px;"
                        src=""
                    ></v-img>
                    <div class="App-main-text-overlap"></div>
                    <div class="App-sub-text-overlap"></div>
                </div>
                <v-row class="pa-0 ma-0">
                    <v-col cols="4" class="pa-0 pa-0" v-for="(aggregate, index) in aggregate" :key="index">
                        <div 
                            class="flip-card pa-4"
                        >
                            <v-card
                                :key="aggregate.key"
                                :to="aggregate.route"
                                @click="changeUrl()"
                                class="mx-auto main-card pa-4"
                                style="text-align: center; border-radius: 10px;"
                                outlined
                            >
                                <div class="d-flex justify-center" style="width:120px; height:120px; border-radius: 10px; margin: 0 auto; background-color:white;">
                                    <v-img style="width:100%; height:100%; object-fit:contain; border-radius: 10px;" :src="aggregate.ImageUrl"></v-img>
                                </div>
                                <div style="text-align: center;">
                                    <h2 class="main-card-title">{{ aggregate.title }}</h2>
                                </div>
                            </v-card>
                        </div>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>

export default {
    name: "App",
    data: () => ({
        useComponent: "",
        drawer: true,
        components: [],
        sideBar: true,
        urlPath: null,
        flipped: [],
        ImageUrl: '',
        aggregate: [
            { 
                title: '접수', 
                description: 'Receiving을 관리하는 화면입니다.', 
                key: 'receivings', 
                route: '/receivings/receivings',
                ImageUrl: '',
            },
            { 
                title: '진료', 
                description: 'consultation을 관리하는 화면입니다.', 
                key: 'consultations', 
                route: '/consultations/consultations',
                ImageUrl: '',
            },
            { 
                title: '보험', 
                description: 'Insurance을 관리하는 화면입니다.', 
                key: 'insurances', 
                route: '/insurances/insurances',
                ImageUrl: '',
            },
            
        ],
        openChatRoom: false,
    }),
    
    async created() {
      var path = document.location.href.split("#/")
      this.urlPath = path[1];

    },
    watch: {
        cards(newCards) {
            this.flipped = new Array(newCards.length).fill(false);
        },
    },

    mounted() {
        var me = this;
        me.components = this.$ManagerLists;
    },

    methods: {
        openSideBar(){
            this.sideBar = !this.sideBar
        },
        changeUrl() {
            var path = document.location.href.split("#/")
            this.urlPath = path[1];
            this.flipped.fill(false);
            this.openChatRoom = false;
        },
        goHome() {
            this.urlPath = null;
        },
        flipCard(index) {
            this.$set(this.flipped, index, true);
        },
        unflipCard(index) {
            this.$set(this.flipped, index, false);
        },
        startChat(){
            this.openChatRoom = true
        }
    }
};
</script>
<style>
</style>
