<template>
  <div id="main">
    <el-tabs :tab-position="tabPosition" style="height: 500px" type="border-card">
      <el-tab-pane label="行列式求值">
        <div class="item">
          <div>说明：输入一个行列式，输出行列式的值</div>
          <el-divider></el-divider>
          <div>格式：行数和列数要相同，两数之间用空格分隔，两行之间以英文状态下的逗号分隔</div>
          <el-divider></el-divider>
          <div style="float: left">
            <el-input
              type="textarea"
              :rows="12"
              style="width: 500px"
              placeholder="请输入行列式"
              resize="none"
              v-model="form.textarea1">
            </el-input>
          </div>
          <div style="float: right; width: 650px; height: 250px">
            <div style="position: relative;top: 110px">输出结果为：
              <el-tag style="width: 150px" v-text="result.result1"></el-tag>
              <el-button type="primary" style="position: relative;left: 10px" @click="determinantCalculation">输出</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="矩阵加法">
        <div class="item">
          <div>说明：输入两个矩阵，输出两个矩阵相加的结果</div>
          <el-divider></el-divider>
          <div>格式：两矩阵必须对应行列数相同，两数之间用空格分隔，两行之间以英文状态下的逗号分隔</div>
          <el-divider></el-divider>
          <div class="main-left">
            <div class="main-div3">
              <el-input
                type="textarea"
                :rows="8"
                style="width: 250px"
                placeholder="请输入矩阵一"
                resize="none"
                v-model="form.textarea3_1">
              </el-input>
              <div
                style="position: relative;
                top: -75px;
                font-size: 40px;
                display:inline"><b>+</b></div>
              <el-input
                type="textarea"
                :rows="8"
                style="width: 250px"
                placeholder="请输入矩阵二"
                resize="none"
                v-model="form.textarea3_2">
              </el-input>
            </div>
          </div>
          <div class="main-right">
            <div class="main-div3">
              <div
                style="position: relative;
                top: -75px;
                font-size: 40px;
                display:inline"><b>=</b></div>
              <el-input
                type="textarea"
                :rows="8"
                style="width: 250px;position: relative;left: 30px"
                resize="none"
                v-model="result.result3">
              </el-input>
              <el-button type="primary" style="position: relative;left: 50px;top: -75px" @click="matrixAddition">输出</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="矩阵乘法">
        <div class="item">
          <div>说明：输入两个矩阵，输出两个矩阵相乘的结果</div>
          <el-divider></el-divider>
          <div>格式：两矩阵必须对应行列数相同,两数之间用空格分隔，两行之间以英文状态下的逗号分隔</div>
          <el-divider></el-divider>
          <div class="main-left">
            <div class="main-div3">
              <el-input
                type="textarea"
                :rows="8"
                style="width: 250px"
                placeholder="请输入矩阵一"
                resize="none"
                v-model="form.textarea4_1">
              </el-input>
              <div
                style="position: relative;
                top: -75px;
                font-size: 40px;
                display:inline"><b>x</b></div>
              <el-input
                type="textarea"
                :rows="8"
                style="width: 250px"
                placeholder="请输入矩阵二"
                resize="none"
                v-model="form.textarea4_2">
              </el-input>
            </div>
          </div>
          <div class="main-right">
            <div class="main-div3">
              <div
                style="position: relative;
                top: -75px;
                font-size: 40px;
                display:inline"><b>=</b></div>
              <el-input
                type="textarea"
                :rows="8"
                style="width: 250px;position: relative;left: 30px"
                resize="none"
                v-model="result.result4">
              </el-input>
              <el-button type="primary" style="position: relative;left: 50px;top: -75px" @click="matrixMultiplication">输出</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="矩阵的转置">
        <div class="item">
          <div>说明：输入一个矩阵，输出转置矩阵</div>
          <el-divider></el-divider>
          <div>格式：两数之间用空格分隔，两行之间以英文状态下的逗号分隔</div>
          <el-divider></el-divider>
          <div class="main-left">
              <el-input
                type="textarea"
                :rows="12"
                style="width: 450px"
                placeholder="请输入矩阵"
                resize="none"
                v-model="form.textarea5">
              </el-input>
            <div
              style="position: relative;
              left: 20px;
              top: -120px;
              font-size: 40px;
              display:inline">
              <el-button type="primary" @click="matrixTranspose">转置</el-button>
            </div>
          </div>
          <div class="main-right">
            <el-input
              type="textarea"
              :rows="12"
              style="width: 450px"
              resize="none"
              v-model="result.result5">
            </el-input>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="逆矩阵">
        <div class="item">
          <div>说明：输入一个矩阵，输出逆矩阵</div>
          <el-divider></el-divider>
          <div>格式：行数和列数要相同，两数之间用空格分隔，两行之间以英文状态下的逗号分隔</div>
          <el-divider></el-divider>
          <div class="main-left">
            <el-input
              type="textarea"
              :rows="12"
              style="width: 450px"
              placeholder="请输入矩阵"
              resize="none"
              v-model="form.textarea6">
            </el-input>
            <div
              style="position: relative;
              left: 20px;
              top: -120px;
              font-size: 40px;
              display:inline">
              <el-button type="primary" @click="inverseMatrix">求逆</el-button>
            </div>
          </div>
          <div class="main-right">
            <el-input
              type="textarea"
              :rows="12"
              style="width: 450px"
              resize="none"
              v-model="result.result6">
            </el-input>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="求矩阵的秩">
        <div class="item">
          <div>说明：输入一个矩阵，输出矩阵的秩</div>
          <el-divider></el-divider>
          <div>格式：两数之间用空格分隔，两行之间以英文状态下的逗号分隔</div>
          <el-divider></el-divider>
          <div style="float: left">
            <el-input
              type="textarea"
              :rows="12"
              style="width: 500px"
              placeholder="请输入矩阵"
              resize="none"
              v-model="form.textarea7">
            </el-input>
          </div>
          <div style="float: right; width: 650px; height: 250px">
            <div style="position: relative;top: 110px">输出结果为：
              <el-tag style="width: 150px" v-text="result.result7"></el-tag>
              <el-button type="primary" style="position: relative;left: 10px" @click="matrixRank">输出</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="线性相关的判断">
        <div class="item">
          <div>说明：输入多组行向量组，输出是否线性相关</div>
          <el-divider></el-divider>
          <div>格式：两数之间用空格分隔，两行之间以英文状态下的逗号分隔</div>
          <el-divider></el-divider>
          <div style="float: left">
            <el-input
              type="textarea"
              :rows="12"
              style="width: 500px"
              placeholder="请输入行向量组"
              resize="none"
              v-model="form.textarea8">
            </el-input>
          </div>
          <div style="float: right; width: 650px; height: 250px">
            <div style="position: relative;top: 110px">输出结果为：
              <el-tag style="width: 150px" v-text="result.result8"></el-tag>
              <el-button type="primary" style="position: relative;left: 10px" @click="linearDependence">输出</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="矩阵的特征值">
        <div class="item">
          <div>说明：输入一个矩阵，输出矩阵的特征值</div>
          <el-divider></el-divider>
          <div>格式：两数之间用空格分隔，两行之间以英文状态下的逗号分隔</div>
          <el-divider></el-divider>
          <div class="main-left">
            <el-input
              type="textarea"
              :rows="12"
              style="width: 450px"
              placeholder="请输入矩阵"
              resize="none"
              v-model="form.textarea9">
            </el-input>
            <div
              style="position: relative;
              left: 20px;
              top: -120px;
              font-size: 40px;
              display:inline">
              <el-button type="primary" @click="eigenvalueMatrix">计算</el-button>
            </div>
          </div>
          <div class="main-right">
            <el-input
              type="textarea"
              :rows="12"
              style="width: 450px"
              resize="none"
              v-model="result.result9">
            </el-input>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="判断矩阵是否相似">
        <div class="item">
          <div>说明：输入两个矩阵，输出矩阵是否相似</div>
          <el-divider></el-divider>
          <div>格式：两数之间用空格分隔，两行之间以英文状态下的逗号分隔</div>
          <el-divider></el-divider>
          <div class="main-left">
            <div class="main-div3">
              <el-input
                type="textarea"
                :rows="8"
                style="width: 250px"
                placeholder="请输入矩阵一"
                resize="none"
                v-model="form.textarea10_1">
              </el-input>
              <div
                style="position: relative;
                top: -75px;
                font-size: 40px;
                display:inline"><b style="opacity: 0">+</b></div>
              <el-input
                type="textarea"
                :rows="8"
                style="width: 250px"
                placeholder="请输入矩阵二"
                resize="none"
                v-model="form.textarea10_2">
              </el-input>
            </div>
          </div>
          <div class="main-right">
            <div style="width: 650px; height: 250px">
              <div style="position: relative;top: 110px">输出结果为：
                <el-tag style="width: 150px" v-text="result.result10"></el-tag>
                <el-button type="primary" style="position: relative;left: 10px" @click="isSimilar">输出</el-button>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
const querystring = require('querystring')
export default {
  name: 'IndexHome',
  data () {
    return {
      tabPosition: 'left',
      form: {
        textarea1: '',
        textarea3_1: '',
        textarea3_2: '',
        textarea4_1: '',
        textarea4_2: '',
        textarea5: '',
        textarea6: '',
        textarea7: '',
        textarea8: '',
        textarea9: '',
        textarea10_1: '',
        textarea10_2: ''
      },
      result: {
        result1: '空',
        result2: '',
        result3: '',
        result4: '',
        result5: '',
        result6: '',
        result7: '空',
        result8: '空',
        result9: '',
        result10: '空'
      }
    }
  },
  methods: {
    formatArr (s) {
      const respArr = s.toString().split(',')
      let res = ''
      for (let i = 0; i < respArr.length; i++) {
        res += respArr[i] + '\n'
      }
      return res
    },
    // 行列式求值
    determinantCalculation () {
      this.postRequest('/determinantCalculation', querystring.stringify(this.form)).then(resp => {
        this.result.result1 = resp.obj
      })
    },
    // 矩阵加法
    matrixAddition () {
      this.postRequest('/matrixAddition', querystring.stringify(this.form)).then(resp => {
        this.result.result3 = this.formatArr(resp.obj)
      })
    },
    // 矩阵乘法
    matrixMultiplication () {
      this.postRequest('/matrixMultiplication', querystring.stringify(this.form)).then(resp => {
        this.result.result4 = this.formatArr(resp.obj)
      })
    },
    // 矩阵转置
    matrixTranspose () {
      this.postRequest('/matrixTranspose', querystring.stringify(this.form)).then(resp => {
        this.result.result5 = this.formatArr(resp.obj)
      })
    },
    // 矩阵的逆
    inverseMatrix () {
      this.postRequest('/inverseMatrix', querystring.stringify(this.form)).then(resp => {
        this.result.result6 = this.formatArr(resp.obj)
      })
    },
    // 矩阵的秩
    matrixRank () {
      this.postRequest('/matrixRank', querystring.stringify(this.form)).then(resp => {
        this.result.result7 = resp.obj
      })
    },
    // 判断行向量组是否线性相关
    linearDependence () {
      this.postRequest('/linearDependence', querystring.stringify(this.form)).then(resp => {
        this.result.result8 = resp.obj
      })
    },
    // 求矩阵的特征值
    eigenvalueMatrix () {
      this.postRequest('/eigenvalueMatrix', querystring.stringify(this.form)).then(resp => {
        const respArr = resp.obj.toString().split(',')
        let res = ''
        for (let i = 0; i < respArr.length; i++) {
          if (respArr[i] !== '0') {
            res += respArr[i]
            if (i < respArr.length - 1) {
              res += ' '
            }
          }
        }
        this.result.result9 = res
      })
    },
    // 判断是否相似
    isSimilar () {
      this.postRequest('/isSimilar', querystring.stringify(this.form)).then(resp => {
        this.result.result10 = resp.obj
      })
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

.item {
  height: 500px;
  width:1200px;
}

.main-left {
  width: 555px;
  height: 300px;
  float: left;
}

.main-right {
  width: 555px;
  height: 300px;
  float: left;
}

.main-div3 {
  position: relative;
  top: 55px;
}
</style>
