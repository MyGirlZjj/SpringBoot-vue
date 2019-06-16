<template>
  <el-dialog title="Edit" v-model="dialogFormVisible" :close-on-click-modal="false" :show-close="false">
    <el-form :model="form">
      <el-form-item label="id" :label-width="formLabelWidth">
        <el-input :disabled="true" v-model="form.id" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="编码" :label-width="formLabelWidth">
        <el-input :disabled="true" v-model="form.bianma" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="品名" :label-width="formLabelWidth">
        <el-input v-model="form.pinming" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item label="规格" :label-width="formLabelWidth">
        <el-input v-model="form.guige" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item label="批号" :label-width="formLabelWidth">
        <el-input v-model="form.pihao" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="有效期" :label-width="formLabelWidth">
        <el-input v-model="form.youxiaoqi" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="单位" :label-width="formLabelWidth">
        <el-input v-model="form.danwei" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="单价" :label-width="formLabelWidth">
        <el-input v-model="form.danjia" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="生产厂家" :label-width="formLabelWidth">
        <el-input v-model="form.shengchanchangjia" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="批准文号" :label-width="formLabelWidth">
        <el-input v-model="form.pizhunwenhao" auto-complete="off"></el-input>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button :plain="true" type="danger" v-on:click="canclemodal">Cancel</el-button>
      <el-button :plain="true" @click="updateForm(form)">Save</el-button>
    </div>
  </el-dialog>
</template>


<script>
  export default {
    data() {
      return {
        formLabelWidth: '120px',
      }
    },
    props: ['dialogFormVisible', 'form'],

    methods: {
      updateForm: function (formName) {
        let itemId = formName.id;
        let phone = formName.phone;
        let zone = formName.zone;
        this.$axios.put('http://127.0.0.1:8000/api/persons/detail/' + itemId, {
          phone: phone,
          zone: zone
        })
          .then(function (response) {
            console.log(response);
            this.form = response.data;

          })
          .catch(function (error) {
            console.log(error);
          });
        location.reload();
      },
      canclemodal: function () {
        this.$emit('canclemodal');
      }
    }

  }

</script>
