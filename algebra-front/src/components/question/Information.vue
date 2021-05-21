<template>
  <el-scrollbar style="height: 500px">
    <div class="main" v-if="$store.state.questionFlag">
      <h1>{{ this.$store.state.questionTitle }}</h1>
      <div>
        <b class="user">
          <i class="el-icon-user"></i>
          {{ this.$store.state.questionUser }}
        </b>
        <span class="time">发布于{{ this.$store.state.questionTime }}</span>
      </div>
      <el-divider></el-divider>
      <div v-html="$store.state.questionContent"></div>
      <div style="height: 30px;"></div>
      <el-divider content-position="center"><span style="font-size: 20px">发表评论</span></el-divider>
      <div style="padding: 20px 0 0">
        <el-input
          :autosize="{ minRows: 3 }"
          placeholder="发表评论"
          v-model="textarea"
          type="textarea"
          show-word-limit
          resize="none"
          style="width: 88%;"
        ></el-input>
        <el-button
          type="primary"
          style="height: 75px;width: 80px; float: right"
          @click="comment"
        >提交</el-button>
      </div>
      <div v-if="!flag">
        <p style="text-align: center; margin: 40px; font-size: 30px; color: #6d6666; opacity: 0.5;">暂无评论</p>
      </div>
      <div v-if="flag">
        <div style="height: 40px;"></div>
        <el-divider content-position="center"><span style="font-size: 20px">用户评论</span></el-divider>
        <template v-for="(discuss,index) in this.discussList">
          <div class="item" :key="index">
            <div class="discuss-user">
              <b style="font-size: 20px;color:dodgerblue;">{{ discuss.username }}</b>
              <span style="font-size: 14px; float:right;">{{ discuss.time }}</span>
              <div style="height: 20px"></div>
            </div>
            <div class="discuss-content">
              <span>{{ discuss.answerContent }}</span>
            </div>
          </div>
          <el-divider :key="index"></el-divider>
        </template>
        <div class="foot">
          <el-pagination
            :hide-on-single-page="true"
            @current-change="handleCurrentChange"
            :current-page="this.answer.currentPage"
            :page-size=this.answer.pageSize
            layout="prev, pager, next"
            :total=this.answer.total>
          </el-pagination>
        </div>
      </div>
    </div>
    </el-scrollbar>
</template>

<script>

export default {
  name: 'information',
  created () {
    this.requestData()
  },
  data () {
    return {
      textarea: '',
      flag: true,
      discussList: '',
      answer: {
        currentPage: 1,
        pageSize: 5,
        total: 0
      }
    }
  },
  methods: {
    async comment () {
      if (this.textarea) {
        await this.postRequest2('/answer', { questionId: this.$store.state.questionId, answerContent: this.textarea, userId: this.$store.state.currentUserId })
        await this.requestData()
        this.textarea = ''
      } else {
        this.$message.error('评论不能为空')
      }
    },
    handleCurrentChange (val) {
      this.answer.currentPage = val
      this.requestData()
    },
    requestData () {
      this.getRequest('/answer/' + this.$store.state.questionId, this.answer).then(resp => {
        this.discussList = resp.obj.records
        this.answer.total = resp.obj.total
        this.flag = true
        if (resp.obj.total === 0) {
          this.flag = false
        }
      })
    }
  },
  computed: {
    reset () {
      return this.$store.state.questionTitle
    }
  },
  watch: {
    reset () {
      this.textarea = ''
      this.requestData()
    }
  }
}
</script>

<style scoped>
.main {
  margin: 10px 30px 10px 10px;
}

.user {
  font-size: 24px;
  color: dodgerblue;
}

.time {
  font-size: 14px;
}

.foot {
  text-align: center;
}

.item {
  padding: 15px 35px 15px 35px;
}

.discuss-user {
}

.discuss-content {
}

</style>
