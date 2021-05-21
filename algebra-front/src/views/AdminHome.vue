<template>
  <div>
    <el-container>
      <el-container>
        <el-aside width="200px">
          <el-menu style="height: 100%" router>
            <template v-for="(item,index) in this.$router.options.routes">
              <el-submenu :index="index + ''" :key="index" v-if="!item.hidden">
                <template slot="title">
                  <i class="el-icon-location"></i><span>{{ item.name }}</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item :key="children.name" :index="children.path" v-for="(children) in item.children">
                    {{ children.name }}
                  </el-menu-item>
                </el-menu-item-group>
              </el-submenu>
            </template>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'AdminHome'
}
</script>

<style scoped>

</style>
