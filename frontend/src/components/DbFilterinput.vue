<template>
  <el-form :inline="true" :model="formInline">

    <el-form-item label="编码">
      <el-input v-model="formInline.bianma" placeholder=""></el-input>
    </el-form-item>

    <el-form-item label="批号">
      <el-input v-model="formInline.pihao" placeholder=""></el-input>
    </el-form-item>

    <el-form-item label="品名">
      <el-input v-model="formInline.pinming" placeholder=""></el-input>
    </el-form-item>

  </el-form>
</template>

<script>
  import lodash from 'lodash'
  import Bus from '../eventBus'

  export default {
    name: 'db-filterinput',
    data() {
      return {
        type_options: [],
        formInline: {
          bianma: '',
          pihao: '',
          pinming: ''
        },
        formLabelWidth: '120px'
      }
    },

    watch: {
      'formInline.sex': 'filterResultData',
      'formInline.email': 'filterResultData'
    },

    methods: {
      selectDemo: function (params) {
        if (params) {
          this.$axios.get("http://127.0.0.1:8000/api/persons/sex")
            .then((response) => {
              this.type_options = response.data;
              console.log(response.data);
            }).catch(function (response) {
            console.log(response)
          });
        }

      },
      filterResultData: _.debounce(
        function () {
          this.$axios.get("http://127.0.0.1:8000/api/persons", {
            params: {
              sex: this.formInline.sex,
              email: this.formInline.email,
            }
          }).then((response) => {
            response.data['sex'] = this.formInline.sex;
            response.data['email'] = this.formInline.email;
            Bus.$emit('filterResultData', response.data);
            console.log(response.data);
          }).catch(function (response) {
            console.log(response)
          });

        },
        500
      ),
    }
  }


</script>
