<template>
  <div id="main">
    <el-tabs :tab-position="tabPosition" style="height: 500px" type="border-card">
      <el-tab-pane label="概述">
        <div class="scroll">
          <el-image :src="srcList[0]" :preview-src-list="srcList"></el-image>
        </div>
      </el-tab-pane>
      <el-tab-pane label="行列式">
        <div class="scroll">
          <el-image :src="srcList[1]" :preview-src-list="srcList"></el-image>
        </div>
      </el-tab-pane>
      <el-tab-pane label="线性方程组">
        <div class="scroll">
          <el-image :src="srcList[2]" :preview-src-list="srcList"></el-image>
        </div>
      </el-tab-pane>
      <el-tab-pane label="矩阵">
        <div class="scroll">
          <el-image :src="srcList[3]" :preview-src-list="srcList"></el-image>
        </div>
      </el-tab-pane>
      <el-tab-pane label="向量">
        <div class="scroll">
          <el-image :src="srcList[4]" :preview-src-list="srcList"></el-image>
        </div>
      </el-tab-pane>
      <el-tab-pane label="特征值">
        <div class="scroll">
          <el-image :src="srcList[5]" :preview-src-list="srcList"></el-image>
        </div>
      </el-tab-pane>
      <el-tab-pane label="二次型">
        <div class="scroll">
          <el-image :src="srcList[6]" :preview-src-list="srcList"></el-image>
        </div>
      </el-tab-pane>
      <el-tab-pane label="线性空间">
        <div class="scroll">
          <el-image :src="srcList[7]" :preview-src-list="srcList"></el-image>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: 'Map',
  data () {
    return {
      tabPosition: 'left',
      srcList: [
        require('../../assets/indexHome/1.png'),
        require('../../assets/indexHome/2.png'),
        require('../../assets/indexHome/3.png'),
        require('../../assets/indexHome/4.png'),
        require('../../assets/indexHome/5.png'),
        require('../../assets/indexHome/6.png'),
        require('../../assets/indexHome/7.png'),
        require('../../assets/indexHome/8.png')
      ]
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

.scroll {
  overflow: auto;
  height: 500px;
  width:1200px;
}
</style>
