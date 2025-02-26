
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ReceivingReceivingManager from "./components/listers/ReceivingReceivingCards"
import ReceivingReceivingDetail from "./components/listers/ReceivingReceivingDetail"

import ConsultationConsultationManager from "./components/listers/ConsultationConsultationCards"
import ConsultationConsultationDetail from "./components/listers/ConsultationConsultationDetail"

import InsuranceInsuranceManager from "./components/listers/InsuranceInsuranceCards"
import InsuranceInsuranceDetail from "./components/listers/InsuranceInsuranceDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/receivings/receivings',
                name: 'ReceivingReceivingManager',
                component: ReceivingReceivingManager
            },
            {
                path: '/receivings/receivings/:id',
                name: 'ReceivingReceivingDetail',
                component: ReceivingReceivingDetail
            },

            {
                path: '/consultations/consultations',
                name: 'ConsultationConsultationManager',
                component: ConsultationConsultationManager
            },
            {
                path: '/consultations/consultations/:id',
                name: 'ConsultationConsultationDetail',
                component: ConsultationConsultationDetail
            },

            {
                path: '/insurances/insurances',
                name: 'InsuranceInsuranceManager',
                component: InsuranceInsuranceManager
            },
            {
                path: '/insurances/insurances/:id',
                name: 'InsuranceInsuranceDetail',
                component: InsuranceInsuranceDetail
            },

    ]
})
