(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0de3b1"],{8586:function(e,t,l){"use strict";l.r(t);var a=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("el-form",{ref:"genInfoForm",attrs:{model:e.info,rules:e.rules,"label-width":"150px"}},[l("el-row",[l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{prop:"tplCategory"}},[l("span",{attrs:{slot:"label"},slot:"label"},[e._v("生成模板")]),l("el-select",{on:{change:e.tplSelectChange},model:{value:e.info.tplCategory,callback:function(t){e.$set(e.info,"tplCategory",t)},expression:"info.tplCategory"}},[l("el-option",{attrs:{label:"单表（增删改查）",value:"crud"}}),l("el-option",{attrs:{label:"树表（增删改查）",value:"tree"}}),l("el-option",{attrs:{label:"主子表（增删改查）",value:"sub"}})],1)],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{prop:"tplWebType"}},[l("span",{attrs:{slot:"label"},slot:"label"},[e._v("前端类型")]),l("el-select",{model:{value:e.info.tplWebType,callback:function(t){e.$set(e.info,"tplWebType",t)},expression:"info.tplWebType"}},[l("el-option",{attrs:{label:"Vue2 Element UI 模版",value:"element-ui"}}),l("el-option",{attrs:{label:"Vue3 Element Plus 模版",value:"element-plus"}})],1)],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{prop:"packageName"}},[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 生成包路径 "),l("el-tooltip",{attrs:{content:"生成在哪个java包下，例如 com.teaching.system",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-input",{model:{value:e.info.packageName,callback:function(t){e.$set(e.info,"packageName",t)},expression:"info.packageName"}})],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{prop:"moduleName"}},[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 生成模块名 "),l("el-tooltip",{attrs:{content:"可理解为子系统名，例如 system",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-input",{model:{value:e.info.moduleName,callback:function(t){e.$set(e.info,"moduleName",t)},expression:"info.moduleName"}})],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{prop:"businessName"}},[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 生成业务名 "),l("el-tooltip",{attrs:{content:"可理解为功能英文名，例如 user",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-input",{model:{value:e.info.businessName,callback:function(t){e.$set(e.info,"businessName",t)},expression:"info.businessName"}})],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{prop:"functionName"}},[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 生成功能名 "),l("el-tooltip",{attrs:{content:"用作类描述，例如 用户",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-input",{model:{value:e.info.functionName,callback:function(t){e.$set(e.info,"functionName",t)},expression:"info.functionName"}})],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{prop:"genType"}},[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 生成代码方式 "),l("el-tooltip",{attrs:{content:"默认为zip压缩包下载，也可以自定义生成路径",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-radio",{attrs:{label:"0"},model:{value:e.info.genType,callback:function(t){e.$set(e.info,"genType",t)},expression:"info.genType"}},[e._v("zip压缩包")]),l("el-radio",{attrs:{label:"1"},model:{value:e.info.genType,callback:function(t){e.$set(e.info,"genType",t)},expression:"info.genType"}},[e._v("自定义路径")])],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 上级菜单 "),l("el-tooltip",{attrs:{content:"分配到指定菜单下，例如 系统管理",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("treeselect",{attrs:{"append-to-body":!0,options:e.menus,normalizer:e.normalizer,"show-count":!0,placeholder:"请选择系统菜单"},model:{value:e.info.parentMenuId,callback:function(t){e.$set(e.info,"parentMenuId",t)},expression:"info.parentMenuId"}})],1)],1),"1"==e.info.genType?l("el-col",{attrs:{span:24}},[l("el-form-item",{attrs:{prop:"genPath"}},[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 自定义路径 "),l("el-tooltip",{attrs:{content:"填写磁盘绝对路径，若不填写，则生成到当前Web项目下",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-input",{model:{value:e.info.genPath,callback:function(t){e.$set(e.info,"genPath",t)},expression:"info.genPath"}},[l("el-dropdown",{attrs:{slot:"append"},slot:"append"},[l("el-button",{attrs:{type:"primary"}},[e._v(" 最近路径快速选择 "),l("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),l("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[l("el-dropdown-item",{nativeOn:{click:function(t){e.info.genPath="/"}}},[e._v("恢复默认的生成基础路径")])],1)],1)],1)],1)],1):e._e()],1),l("el-row",{directives:[{name:"show",rawName:"v-show",value:"tree"==e.info.tplCategory,expression:"info.tplCategory == 'tree'"}]},[l("h4",{staticClass:"form-header"},[e._v("其他信息")]),l("el-col",{attrs:{span:12}},[l("el-form-item",[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 树编码字段 "),l("el-tooltip",{attrs:{content:"树显示的编码字段名， 如：dept_id",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-select",{attrs:{placeholder:"请选择"},model:{value:e.info.treeCode,callback:function(t){e.$set(e.info,"treeCode",t)},expression:"info.treeCode"}},e._l(e.info.columns,(function(e,t){return l("el-option",{key:t,attrs:{label:e.columnName+"："+e.columnComment,value:e.columnName}})})),1)],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 树父编码字段 "),l("el-tooltip",{attrs:{content:"树显示的父编码字段名， 如：parent_Id",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-select",{attrs:{placeholder:"请选择"},model:{value:e.info.treeParentCode,callback:function(t){e.$set(e.info,"treeParentCode",t)},expression:"info.treeParentCode"}},e._l(e.info.columns,(function(e,t){return l("el-option",{key:t,attrs:{label:e.columnName+"："+e.columnComment,value:e.columnName}})})),1)],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 树名称字段 "),l("el-tooltip",{attrs:{content:"树节点的显示名称字段名， 如：dept_name",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-select",{attrs:{placeholder:"请选择"},model:{value:e.info.treeName,callback:function(t){e.$set(e.info,"treeName",t)},expression:"info.treeName"}},e._l(e.info.columns,(function(e,t){return l("el-option",{key:t,attrs:{label:e.columnName+"："+e.columnComment,value:e.columnName}})})),1)],1)],1)],1),l("el-row",{directives:[{name:"show",rawName:"v-show",value:"sub"==e.info.tplCategory,expression:"info.tplCategory == 'sub'"}]},[l("h4",{staticClass:"form-header"},[e._v("关联信息")]),l("el-col",{attrs:{span:12}},[l("el-form-item",[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 关联子表的表名 "),l("el-tooltip",{attrs:{content:"关联子表的表名， 如：sys_user",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-select",{attrs:{placeholder:"请选择"},on:{change:e.subSelectChange},model:{value:e.info.subTableName,callback:function(t){e.$set(e.info,"subTableName",t)},expression:"info.subTableName"}},e._l(e.tables,(function(e,t){return l("el-option",{key:t,attrs:{label:e.tableName+"："+e.tableComment,value:e.tableName}})})),1)],1)],1),l("el-col",{attrs:{span:12}},[l("el-form-item",[l("span",{attrs:{slot:"label"},slot:"label"},[e._v(" 子表关联的外键名 "),l("el-tooltip",{attrs:{content:"子表关联的外键名， 如：user_id",placement:"top"}},[l("i",{staticClass:"el-icon-question"})])],1),l("el-select",{attrs:{placeholder:"请选择"},model:{value:e.info.subTableFkName,callback:function(t){e.$set(e.info,"subTableFkName",t)},expression:"info.subTableFkName"}},e._l(e.subColumns,(function(e,t){return l("el-option",{key:t,attrs:{label:e.columnName+"："+e.columnComment,value:e.columnName}})})),1)],1)],1)],1)],1)},o=[],n=l("ca17"),s=l.n(n),i=(l("542c"),{components:{Treeselect:s.a},props:{info:{type:Object,default:null},tables:{type:Array,default:null},menus:{type:Array,default:[]}},data:function(){return{subColumns:[],rules:{tplCategory:[{required:!0,message:"请选择生成模板",trigger:"blur"}],packageName:[{required:!0,message:"请输入生成包路径",trigger:"blur"}],moduleName:[{required:!0,message:"请输入生成模块名",trigger:"blur"}],businessName:[{required:!0,message:"请输入生成业务名",trigger:"blur"}],functionName:[{required:!0,message:"请输入生成功能名",trigger:"blur"}]}}},watch:{"info.subTableName":function(e){this.setSubTableColumns(e)},"info.tplWebType":function(e){""===e&&(this.info.tplWebType="element-ui")}},methods:{normalizer:function(e){return e.children&&!e.children.length&&delete e.children,{id:e.menuId,label:e.menuName,children:e.children}},subSelectChange:function(e){this.info.subTableFkName=""},tplSelectChange:function(e){"sub"!==e&&(this.info.subTableName="",this.info.subTableFkName="")},setSubTableColumns:function(e){for(var t in this.tables){var l=this.tables[t].tableName;if(e===l){this.subColumns=this.tables[t].columns;break}}}}}),r=i,c=l("2877"),p=Object(c["a"])(r,a,o,!1,null,null,null);t["default"]=p.exports}}]);