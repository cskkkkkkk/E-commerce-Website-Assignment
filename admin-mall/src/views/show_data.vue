<template>
  <div id="main" style="width: 100%; height: 450px;"></div>
  <div id="main1" style="width: 100%; height: 450px;"></div>
</template>

<script>
import { onMounted, reactive } from 'vue';
import * as echarts from 'echarts';

export default {
  name: 'MyChart',
  setup() {
    const state = reactive({
      people: 2,
      view: 100,
      sale: 48770.1,
      earn: 10492.2,
      orders: 10
    });

    onMounted(() => {
      const chartDom = document.getElementById('main');
      const myChart = echarts.init(chartDom, 'dark');
      const chartDom1 = document.getElementById('main1');
      const myChart1 = echarts.init(chartDom1, 'dark');

      // 第一个图表的配置
      const option = {
        xAxis: {
          type: 'category',
          data: ['活跃人数', '点击量', '订单量']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [state.people, state.view, state.orders],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            }
          }
        ],
        tooltip: {
          trigger: 'item',  // 点击柱形图时显示
          formatter: '{b}: {c}' // 显示名称和对应的数值
        }
      };

      // 第二个图表的配置
      const option1 = {
        xAxis: {
          type: 'category',
          data: ['销售金额', '盈利']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [state.sale, state.earn],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            }
          }
        ],
        tooltip: {
          trigger: 'item',  // 点击柱形图时显示
          formatter: '{b}: {c}' // 显示名称和对应的数值
        }
      };

      myChart.setOption(option);
      myChart1.setOption(option1);

      // 为图表绑定点击事件，显示 Y 轴的数据
      myChart.on('click', function (params) {
        const clickedData = params.data;
        alert(`Clicked on ${params.name}: ${clickedData}`);  // 弹出点击的柱形图的数据
      });

      myChart1.on('click', function (params) {
        const clickedData = params.data;
        alert(`Clicked on ${params.name}: ${clickedData}`);  // 弹出点击的柱形图的数据
      });
    });
  }
};
</script>



<style scoped>
#main {
  width: 100%;
  height: 400px;
}

</style>
