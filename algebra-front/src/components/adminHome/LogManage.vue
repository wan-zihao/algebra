<template>
  <div>
    <div class="block">
      <el-timeline>
        <el-timeline-item
          v-for="(activity, index) in log.messageData"
          type="primary"
          :key="index"
          :timestamp="activity.time">
          <div style="font-size: 15px"><b>{{activity.messageContent}}</b></div>
        </el-timeline-item>
      </el-timeline>
    </div>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        :current-page="this.log.currentPage"
        :page-sizes="[6, 9, 12, 15]"
        :page-size=this.log.pageSize
        layout="total, sizes, prev, pager, next, jumper"
        :total=this.log.total>
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LogManage',
  data () {
    return {
      log: {
        total: 0,
        currentPage: 1,
        pageSize: 9,
        messageData: []
      }
    }
  },
  created () {
    // 页面创建时填充数据
    this.getRequest('/message', this.log).then(resp => {
      this.log.total = resp.obj.total
      this.log.messageData = resp.obj.records
    })
  },
  methods: {
    handleCurrentChange (val) {
      this.log.currentPage = val
      this.getRequest('/message', this.log).then(resp => {
        this.log.messageData = resp.obj.records
      })
    },
    handleSizeChange (val) {
      this.log.pageSize = val
      this.getRequest('/message', this.log).then(resp => {
        this.log.messageData = resp.obj.records
      })
    }
  }
}
</script>

<style scoped>

</style>
