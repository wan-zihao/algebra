<template>
    <div id="main">
      <div id="suggest">
        <div id="title">
          线代学习建议
        </div>
        <br/>
        <el-divider></el-divider>
        ①由易而难 线性代数常常涉及大型数组，故先将容易的问题搞明白，再解决有难度的问题，例如行列式定义，首先将3阶行列式定义理解好，自然可以推广到n阶行列式情形；<br/>
        <el-divider></el-divider>
        ②由低而高 运用技巧，省时不少，无论是行列式还是矩阵，在低阶状态，找出适合的计算方法，则可自如推广运用到高阶情形；<br/>
        <el-divider></el-divider>
        ③由简而繁 一些运算法则，先试用于简单情形，进而应用于复杂问题，例如，克莱姆法则，线性方程组解存在性判别，对角化问题等等；<br/>
        <el-divider></el-divider>
        ④由浅而深线性代数中一些新概念如秩，特征值特征向量，应当先理解好它们的定义，才能深刻理解它们与其他概念的联系、它们的作用，一步步达到运用自如境地。
        <el-divider></el-divider>
        ⑤要做到理解概念、牢记公式、注意关联、掌握方法，特别要注意对象之间，定义运算之间的比较和关联，例如方阵和行列式的联系，矩阵多项式与一般多项式的比较、数组运算与数字运算的差异（如矩阵乘法、求逆)
        <!--<el-divider></el-divider>-->
        <!--⑥初等变换在线性代数中具有重要地位，初等变换方法几乎贯穿全程，计算行列式、求矩阵的秩和矩阵的逆、解方程组，讨论线性相关性等等，都要用到它，运用该方法要注意培养运算能力，认真细心是非常必要的-->
        <!--<el-divider></el-divider>-->
        <!--⑦听讲、看书、记忆、练习加上多思是学好线性代数的基本保证-->
        <!--<el-divider></el-divider>-->
        <!--⑧学会使用数学软件mathematica对于巩固所学知识，加深理解，简化计算会有所帮助-->
        <!--<el-divider></el-divider>-->
      </div>
    </div>
</template>

<script>
export default {
  name: 'Suggest'
}
</script>

<style scoped>
#main {
  margin: 0 auto;
  width: 1300px;
  height: 500px;
  border: 1px solid #d7cdcd;
  box-shadow: 0 0 25px #cac6c6;
  overflow: auto;
}

#suggest {
  margin: 0 auto;
  padding: 15px 0px 15px 15px;
  width: 1290px;
}

#title {
  font-size: 26px;
}
</style>
