// //可以更方便地写出类型安全的配置文件
// import { defineConfig } from 'vite'
// //用来支持 Vue 3 的编译和构建
// import vue from '@vitejs/plugin-vue'
// //用来处理和解析文件路径，常用于配置别名或路径重写
// import path from 'path'
// //是一个 Vite 插件，自动按需导入 Vue 组件。它会根据你在模板中使用的组件，自动引入相关的组件库，而不需要手动在脚本中进行 import。
// import Components from 'unplugin-vue-components/vite'
// //插件的一个解析器，用于支持 Element Plus 组件库的按需加载。它会自动按需导入 Element Plus 组件库中的组件
// import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
// import ElementPlus from 'unplugin-element-plus/vite' // 不加这个配置，ElMessage出不来
//
// // https://vitejs.dev/config/
// // eslint-disable-next-line no-unused-vars
// export default ({ mode }) => defineConfig({
//     //列出了项目中需要的 Vite 插件。
//     plugins: [
//         vue(),
//         // 按需引入，主题色的配置，需要加上 importStyle: 'sass'
//         Components({
//             resolvers: [ElementPlusResolver({
//                 importStyle: 'sass'  //// 按需引入 Element Plus 的组件和样式
//             })],
//         }),
//         ElementPlus()//引入插件
//     ],
//     resolve: {
//         alias: {
//             '~': path.resolve(__dirname, './'),//  '~' 指代项目根目录
//             '@': path.resolve(__dirname, 'src')// '@' 指代 'src' 目录
//         },
//     },
//     base: './',//项目资源会相对于当前目录加载。
//
//     //于设置 Vite 开发服务器的行为
//     server: {
//         proxy: {
//             '/api': {
//                 target: 'http://backend-api-02.newbee.ltd/manage-api/v1', // 凡是遇到 /api 路径的请求，都映射到 target 属性
//                 changeOrigin: true,
//                 rewrite: path => path.replace(/^\/api/, '') // 重写 api 为 空，就是去掉它
//             }
//         }
//     },
//     // 配置在每个 SCSS 文件中自动引入 @/styles/element/index.scss，可以覆盖或定制 Element Plus 的主题变量。
//     css: {
//         preprocessorOptions: {
//             // 覆盖掉element-plus包中的主题变量文件
//             scss: {
//                 additionalData: `@use "@/styles/element/index.scss" as *;`,
//             },
//         },
//     },
// })
