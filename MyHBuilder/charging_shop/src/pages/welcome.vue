<template>
	<canvas id="canvas"></canvas>
</template>

<script>
	//import { object_lizi } from '../assets/js/object_lizi'
	export default{
		data(){
			return{

			}
		},
        created() {

        },
		mounted() {
            // let canvas = document.getElementById('canvas');
            // let ctx = canvas.getContext('2d');
		    // let mainContentW = document.getElementsByClassName('main_content')[0].offsetWidth;
		    // let mainContentH = document.getElementsByClassName('main_content')[0].offsetHeight;
		    // canvas.width = mainContentW;
		    // canvas.height = mainContentH;
            // let liziArray = [];
		    // for(let i=0; i < 45; i++) {
		    //     let lizis = new object_lizi(mainContentW, mainContentH, ctx);
		    //     lizis.drawArc();
		    //     liziArray.push(lizis);
			// }
			// function anim() {
		    //     ctx.fillStyle = 'rgba(240, 240, 240, .6)';
		    //     ctx.fillRect(0, 0, mainContentW, mainContentH);
		    //     ctx.save();
            //     ctx.fillStyle = "#000000";
			// 	ctx.font = "25px Arial";
			// 	ctx.shadowColor = "#808080";
			// 	ctx.shadowBlur = 10;
			// 	ctx.shadowOffsetX = 10;
			// 	ctx.shadowOffsetY = 5;
			// 	ctx.fillText("欢迎使用充电通综合后台管理系统",10,40);
			// 	ctx.restore();

		    //     for (let i=0,len=liziArray.length; i < len; i++) {
		    //         liziArray[i].move();
			// 	}
            //     (requestAnimationFrame || webkitRequestAnimationFrame)(anim);
			// }
            // anim();
		},
		methods: {

		},
	}
</script>

<style scoped lang="less">
	#canvas {
		width: 100%;
		height: 100%;
	}
</style>