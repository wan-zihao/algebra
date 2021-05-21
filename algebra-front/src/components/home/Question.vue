<template>
  <div id="main">
    <div id="questionList">
      <el-input style="width: 74%" type="text" placeholder="搜索问题 / 文章" prefix-icon="el-icon-search" v-model="question.search" auto-complete="false" @blur="search"></el-input>
      <router-link tag="el-button" to="/question">提问 / 写文章</router-link>
      <el-scrollbar style="height: 420px">
      <template v-for="(question,index) in this.questionList">
        <div class="item" :key="index" @click="lookInformation(question.questionTitle, question.username, question.time, question.questionContent, question.questionId)">
          <div class="item-title">
            <b>{{ question.questionTitle }}</b>
            <div style="height: 20px"></div>
          </div>
          <div class="item-content">
            <span v-html="textContent(question.questionContent)"></span>
          </div>
        </div>
        <el-divider :key="index"></el-divider>
      </template>
      </el-scrollbar>
      <div class="foot">
        <el-pagination
          :hide-on-single-page="true"
          @current-change="handleCurrentChange"
          :current-page="this.question.currentPage"
          :page-size=this.question.pageSize
          layout="prev, pager, next"
          :total=this.question.total>
        </el-pagination>
      </div>
    </div>
    <el-divider direction="vertical"></el-divider>
    <div id="writeQuestion">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Question',
  created () {
    this.requestData()
  },
  data () {
    return {
      questionList: [],
      question: {
        total: 0,
        pageSize: 10,
        currentPage: 1,
        search: ''
      }
    }
  },
  methods: {
    search () {
      this.question.currentPage = 1
      this.requestData()
    },
    requestData () {
      this.getRequest('/question', this.question).then(resp => {
        this.question.total = resp.obj.total
        this.questionList = resp.obj.records
      })
    },
    handleCurrentChange (val) {
      this.question.currentPage = val
      this.requestData()
    },
    async lookInformation (title, user, time, content, id) {
      this.$store.commit('modifyQuestionTitle', title)
      this.$store.commit('modifyQuestionUser', user)
      this.$store.commit('modifyQuestionTime', time)
      this.$store.commit('modifyQuestionContent', content)
      this.$store.commit('modifyQuestionFlag', true)
      this.$store.commit('modifyQuestionId', id)
      await this.$router.push(
        { path: '/Information' },
        onComplete => {},
        onAbort => {}
      )
    }
  },
  computed: {
    textContent () {
      return content => {
        return content.toString().replace(/<[^>]+>/g, '')
      }
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
}

#questionList {
  float: left;
  /*border: #048BE5 solid 1px;*/
  width: 500px;
  height: 500px;
  overflow-x: hidden;
}

#writeQuestion {
  float: right;
  /*border: red solid 1px;*/
  width: 775px;
  height: 500px;
  overflow: auto;
}

.el-divider--vertical {
  display: inline-block;
  width: 2px;
  height: 500px;
  margin: 0 8px;
  vertical-align: middle;
  position: relative;
}

.el-divider--horizontal{
  margin: 0;
  padding: 0;
}

.item:hover {
  background-color: rgba(225, 230, 239, 0.59);
  cursor: pointer;
}
.item {
  padding: 15px 35px 15px 35px;
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
