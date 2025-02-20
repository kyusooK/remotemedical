<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '접수' }" />
        <VerticalNavLink
            :item="{
                title: '접수',
                to: '/receivings/receivings',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '진료' }" />
        <VerticalNavLink
            :item="{
                title: '진료',
                to: '/consultations/consultations',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '보험' }" />
        <VerticalNavLink
            :item="{
                title: '보험',
                to: '/insurances/insurances',
            }"
        />
    </ul>
</template>
