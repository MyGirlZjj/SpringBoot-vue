<template>
    <li class="el-menu-item is-active" style="">{{msg}}</li>
<!--    <li class="el-menu-item" style="">{{msg1}}</li>-->
<!--    <li class="el-menu-item is-active" style="">{{msg}}</li>-->
</template>

<script>

    export default {
        name: 'db-sidebar',
        data() {
            return {
                msg: '药品信息'
            }
        }
    }

</script>

<style>
    li {
        padding-left: 20px;
    }
</style>
