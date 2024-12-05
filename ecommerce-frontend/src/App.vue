<template>
  <div style="width: 100%;height: 100%">
    <router-view></router-view>
  </div>
</template>
<script>

import {defineComponent} from "vue";

export default defineComponent({
  components: {}
})
</script>
<style>
.el-message {
  width: 400px;   /* 设置消息框宽度 */
  height: 100px;  /* 设置消息框高度 */
  padding: 15px;  /* 设置内边距 */

}


</style>