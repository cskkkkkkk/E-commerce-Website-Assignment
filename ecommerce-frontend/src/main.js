import  {createApp} from 'vue'
import App from './App.vue'
import router from './router/index.js'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import Vant from 'vant';
import {store} from "core-js/internals/reflect-metadata";
const  app=createApp(App);
app.use(router);
app.use(ElementPlus);
app.config.productionTip=false;
app.use(Vant);
app.use(store);
app.mount('#app');