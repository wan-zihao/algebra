<template>
  <div id="main">
    <el-button @click="dialogVisible = true" style="height: 45px;width: 100%;color: dodgerblue">点击上传文件</el-button>
    <el-scrollbar style="height: 420px">
      <div v-if="algorithmList.length === 0">
        <h1 style="text-align: center">当前无算法文件</h1>
      </div>
    <template v-for="(algorithm,index) in this.algorithmList">
      <div class="item" :key="index" @click="download(algorithm.uuid)">
        <div class="item-title">
          <b style="color: dodgerblue; font-size: 24px"><i class="el-icon-user"></i>{{ algorithm.username }}</b>
          <span><span style="font-size: 12px">上传于</span>{{ algorithm.uploadTime }}</span>
        </div>
        <div class="item-content">
          {{ algorithm.algorithmContent }}
        </div>
        <el-divider :key="index"></el-divider>
      </div>
    </template>
    </el-scrollbar>
    <div class="foot">
      <el-pagination
        :hide-on-single-page="true"
        @current-change="handleCurrentChange"
        :current-page="this.algorithm.currentPage"
        :page-size=this.algorithm.pageSize
        layout="prev, pager, next"
        :total=this.algorithm.total>
      </el-pagination>
    </div>
    <!--文件上传模态框-->
    <el-dialog
      title="文件上传"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-upload
        :headers="headers"
        drag
        action="/upload"
        multiple
        accept=".txt">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传txt文件，且不超过1MB</div>
      </el-upload>
      <span slot="footer" class="dialog-footer"></span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Algorithm',
  created () {
    this.requestData()
  },
  data () {
    return {
      algorithmList: [],
      dialogVisible: false,
      algorithm: {
        total: 0,
        currentPage: 1,
        pageSize: 5,
        uuid: ''
      },
      headers: {
        contentType: 'multipart/form-data',
        Authorization: window.sessionStorage.getItem('tokenStr')
      }
    }
  },
  methods: {
    requestData () {
      this.getRequest('/algorithm', this.algorithm).then(resp => {
        this.algorithm.total = resp.obj.total
        this.algorithmList = resp.obj.records
      })
    },
    handleCurrentChange (val) {
      this.algorithm.currentPage = val
      this.requestData()
    },
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {
        })
    },
    download (uuid) {
      this.algorithm.uuid = uuid
      const url = '/download?uuid=' + uuid
      window.location.href = url
    }
  }
}
</script>

<style scoped>
#main {
  margin: 0 auto;
  width: 1300px;
  height: 500px;
  border: 1px solid #d7cdcd;
  box-shadow: 0 0 25px #cac6c6;
  overflow-x: hidden;
}

.el-divider--horizontal{
  margin: 10px 0 0 0;
  padding: 0;
}

.item:hover {
  background-color: #e1e7e7;
  cursor: pointer;
}

.item {
  padding: 15px 35px 0px 35px;
}

.item-title {
  white-space: nowrap;
  overflow:hidden;
  text-overflow: ellipsis;
  height: 40px;
}

.item-content {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  line-clamp: 2;
  -webkit-box-orient: vertical;
  color: #6d6666;
}

.foot {
  text-align: center;
}

/deep/ .el-scrollbar__wrap {
  overflow-x: hidden;
}
</style>
