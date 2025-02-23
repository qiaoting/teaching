(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0cfa79"],{6548:function(t,e,n){"use strict";n.r(e);var s=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-container"},[n("el-row",{staticClass:"mb8",attrs:{gutter:10}},[n("el-col",{attrs:{span:1.5}},[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["bbs:post:add"],expression:"['bbs:post:add']"}],attrs:{type:"primary",plain:"",icon:"el-icon-plus",size:"mini"},on:{click:t.handleAdd}},[t._v("新增帖子")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["bbs:post:remove"],expression:"['bbs:post:remove']"}],attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"mini",disabled:t.multiple},on:{click:t.handleDelete}},[t._v("删除")])],1),n("el-col",{attrs:{span:1.5}},[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["bbs:post:export"],expression:"['bbs:post:export']"}],attrs:{type:"warning",plain:"",icon:"el-icon-download",size:"mini"},on:{click:t.handleExport}},[t._v("导出")])],1)],1),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],attrs:{data:t.postList},on:{"selection-change":t.handleSelectionChange}},[n("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-row",[n("span",[t._v(t._s(e.row.postTitle))])]),n("el-row",[n("span",{domProps:{innerHTML:t._s(e.row.postContent)}})])]}}])}),n("el-table-column",{attrs:{label:"标题",align:"center",prop:"postTitle"}}),n("el-table-column",{attrs:{label:"内容",align:"center",prop:"postContent"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("div",{domProps:{innerHTML:t._s(t.truncateText(e.row.postContent,20))}})]}}])}),n("el-table-column",{attrs:{label:"作者",align:"center",prop:"nickName"}}),n("el-table-column",{attrs:{label:"发布时间",align:"center",prop:"createTime"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("i",{staticClass:"el-icon-time"}),n("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.createTime))])]}}])}),n("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["bbs:post:remove"],expression:"['bbs:post:remove']"},{name:"hasRole",rawName:"v-hasRole",value:["admin","teacher"],expression:"['admin','teacher']"}],attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(n){return t.handleDelete(e.row)}}},[t._v("删除")])]}}])})],1),n("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.queryParams.pageNum,limit:t.queryParams.pageSize},on:{"update:page":function(e){return t.$set(t.queryParams,"pageNum",e)},"update:limit":function(e){return t.$set(t.queryParams,"pageSize",e)},pagination:t.getList}}),n("el-dialog",{attrs:{title:t.title,visible:t.open,width:"700px","append-to-body":""},on:{"update:visible":function(e){t.open=e}}},[n("el-form",{ref:"form",attrs:{model:t.form,rules:t.rules,"label-width":"80px"}},[n("el-form-item",{attrs:{label:"标题",prop:"postTitle"}},[n("el-input",{attrs:{placeholder:"请输入标题"},model:{value:t.form.postTitle,callback:function(e){t.$set(t.form,"postTitle",e)},expression:"form.postTitle"}})],1),n("el-form-item",{attrs:{label:"内容",prop:"postContent"}},[n("editor",{attrs:{"min-height":250},model:{value:t.form.postContent,callback:function(e){t.$set(t.form,"postContent",e)},expression:"form.postContent"}})],1)],1),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:t.submitForm}},[t._v("确 定")]),n("el-button",{on:{click:t.cancel}},[t._v("取 消")])],1)],1)],1)},a=[],o=n("5530"),l=(n("d81d"),n("fb6a"),n("d3b7"),n("ac1f"),n("5319"),n("0643"),n("a573"),n("b775"));function i(t){return Object(l["a"])({url:"/bbs/post/list",method:"get",params:t})}function r(t){return Object(l["a"])({url:"/bbs/post/"+t,method:"get"})}function u(t){return Object(l["a"])({url:"/bbs/post",method:"post",data:t})}function c(t){return Object(l["a"])({url:"/bbs/post",method:"put",data:t})}function p(t){return Object(l["a"])({url:"/bbs/post/"+t,method:"delete"})}var m={name:"Post",dicts:["sys_normal_disable"],data:function(){return{loading:!0,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,postList:[],title:"",open:!1,queryParams:{pageNum:1,pageSize:10,categoryId:null,userId:null,postTitle:null,postContent:null,status:null},form:{},rules:{postTitle:[{required:!0,trigger:"blur",message:"请填写标题"},{min:2,max:50,message:"标题长度必须介于 2 和 50 之间",trigger:"blur"}],postContent:[{required:!0,trigger:"blur",message:"请填写内容"},{min:2,max:1e3,message:"内容长度必须介于 2 和 1000 之间",trigger:"blur"}]}}},created:function(){this.getList()},methods:{getList:function(){var t=this;this.loading=!0,i(this.queryParams).then((function(e){t.postList=e.rows,t.total=e.total,t.loading=!1}))},cancel:function(){this.open=!1,this.reset()},reset:function(){this.form={postId:null,categoryId:null,userId:null,postTitle:null,postContent:null,status:"0",createBy:null,createTime:null,updateBy:null,updateTime:null,remark:null},this.resetForm("form")},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange:function(t){this.ids=t.map((function(t){return t.postId})),this.single=1!==t.length,this.multiple=!t.length},handleAdd:function(){this.reset(),this.open=!0,this.title="添加帖子"},handleUpdate:function(t){var e=this;this.reset();var n=t.postId||this.ids;r(n).then((function(t){e.form=t.data,e.open=!0,e.title="修改帖子"}))},submitForm:function(){var t=this;this.$refs["form"].validate((function(e){e&&(null!=t.form.postId?c(t.form).then((function(e){t.$modal.msgSuccess("修改成功"),t.open=!1,t.getList()})):u(t.form).then((function(e){t.$modal.msgSuccess("新增成功"),t.open=!1,t.getList()})))}))},truncateText:function(t,e){var n=t.replace(/<\/?[^>]+(>|$)/g,"");return n.length>e?n.slice(0,e)+"...":n},handleDelete:function(t){var e=this,n=t.postId||this.ids;this.$modal.confirm('是否确认删除帖子编号为"'+n+'"的数据项？').then((function(){return p(n)})).then((function(){e.getList(),e.$modal.msgSuccess("删除成功")})).catch((function(){}))},handleExport:function(){this.download("bbs/post/export",Object(o["a"])({},this.queryParams),"post_".concat((new Date).getTime(),".xlsx"))}}},d=m,h=n("2877"),f=Object(h["a"])(d,s,a,!1,null,null,null);e["default"]=f.exports}}]);