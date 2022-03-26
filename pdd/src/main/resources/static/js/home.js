// new Vue().$mount('#app');
new Vue({
    el : "#app",
    data(){
        return {
            screenHeight : {height : ''},
            treeCollapse:false,                                                                                             // 菜单 打开关闭
        };
    },
    created(){

    },
    mounted(){
        this.initPage();

    },
    destroyed(){

    },
    methods:{
        /**
         * 初始化页面 渲染
         */
        initPage(){
            const  that = this;
            // 获取屏幕高度
            that.screenHeight.height = ( window.innerHeight - 10 ) + 'px';
        },
    }
});