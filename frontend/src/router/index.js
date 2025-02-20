import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/receivings/receivings',
      component: () => import('../components/ui/ReceivingGrid.vue'),
    },
    {
      path: '/hospitals/consultations',
      component: () => import('../components/ui/ConsultationGrid.vue'),
    },
    {
      path: '/insurances/insurances',
      component: () => import('../components/ui/InsuranceGrid.vue'),
    },
  ],
})

export default router;
