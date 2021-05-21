<template>
  <div>
    <div v-if="algorithmList.length === 0">
      <h1 style="text-align: center">当前无算法文件</h1>
    </div>
    <el-scrollbar style="height: 540px">
      <template v-for="(algorithm,index) in this.algorithmList">
        <div class="item" :key="index">
          <div class="item-title">
            <b style="color: dodgerblue; font-size: 24px"><i class="el-icon-user"></i>{{ algorithm.username }}</b>
            <span><span style="font-size: 12px">上传于</span>{{ algorithm.uploadTime }}</span>
            <div style="float:right">
              <el-button type="text" @click="deleteAlgorithm(algorithm.algorithmId, algorithm.uuid)">删除</el-button>
            </div>
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
  </div>
</template>

<script>
export default {
  name: 'AlgorithmManage',
  created () {
    this.requestData()
  },
  data () {
    return {
      algorithmList: [],
      algorithm: {
        total: 0,
        currentPage: 1,
        pageSize: 6,
        uuid: ''
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
    async deleteAlgorithm (id, uuid) {
      await this.deleteRequest('/algorithm/' + id, { uuid: uuid })
      await this.requestData()
    }
  }
}
</script>

<style scoped>

.el-divider--horizontal{
  margin: 10px 0 0 0;
  padding: 0;
}

.item:hover {
  background-color: #e1e7e7;
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
