CREATE TABLE `bus_bbs_post` (
    `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '帖子',
    `user_id` bigint DEFAULT NULL COMMENT '用户',
    `nick_name` varchar(30)  DEFAULT NULL,
    `post_title` varchar(100)  NOT NULL COMMENT '标题',
    `post_content` varchar(1000)  DEFAULT NULL COMMENT '内容',
    `status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1关闭）',
    `create_by` varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255)  DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='帖子表';

--
-- Table structure for table `bus_course`
--

DROP TABLE IF EXISTS bus_course;


CREATE TABLE bus_course (
course_id int NOT NULL AUTO_INCREMENT COMMENT '课程',
course_name varchar(250)  NOT NULL COMMENT '课程名称',
`status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1关闭）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(255)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (course_id) USING BTREE,
UNIQUE KEY uk_coursename (course_name)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='课程表';


--
-- Table structure for table `bus_homework`
--

DROP TABLE IF EXISTS bus_homework;


CREATE TABLE bus_homework (
homework_id int NOT NULL AUTO_INCREMENT COMMENT '作业ID',
dept_id bigint DEFAULT NULL COMMENT '班级',
course_id int DEFAULT NULL COMMENT '课程',
homework_title varchar(50)  DEFAULT NULL COMMENT '作业标题',
homework_type char(1)  DEFAULT NULL COMMENT '作业类型（1通知 2公告）',
homework_content longblob COMMENT '作业内容',
`status` char(1)  DEFAULT '0' COMMENT '作业状态（0正常 1关闭）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(255)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (homework_id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='作业布置';


--
-- Table structure for table `bus_schedule`
--

DROP TABLE IF EXISTS bus_schedule;


CREATE TABLE bus_schedule (
schedule_id int NOT NULL AUTO_INCREMENT COMMENT '课程表',
schedule_name varchar(250)  NOT NULL COMMENT '课程表名称',
`status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1关闭）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(255)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (schedule_id),
UNIQUE KEY uk_name (schedule_name)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='课程表';


--
-- Table structure for table `bus_schedule_detail`
--

DROP TABLE IF EXISTS bus_schedule_detail;


CREATE TABLE bus_schedule_detail (
id bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
schedule_id bigint NOT NULL COMMENT '课程表',
course_id int DEFAULT NULL COMMENT '课程',
dept_id bigint DEFAULT NULL COMMENT '班级',
teacher_id bigint DEFAULT NULL COMMENT '教师',
day_week varchar(200)  DEFAULT NULL COMMENT '周几',
hour_day varchar(200)  DEFAULT NULL COMMENT '第几节课',
`status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1关闭）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(255)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='课表明细';


--
-- Table structure for table `bus_score`
--

DROP TABLE IF EXISTS bus_score;


CREATE TABLE bus_score (
id bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
test_id int DEFAULT NULL COMMENT '考试批次',
student_id bigint DEFAULT NULL COMMENT '学号',
course_id int DEFAULT NULL COMMENT '课程',
score float DEFAULT NULL COMMENT '成绩',
`status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1关闭）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(255)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9401 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='成绩表';


--
-- Table structure for table `bus_stu_study`
--

DROP TABLE IF EXISTS bus_stu_study;


CREATE TABLE bus_stu_study (
id bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
course_id int DEFAULT NULL COMMENT '课程',
dept_id bigint DEFAULT NULL COMMENT '部门',
user_id bigint DEFAULT NULL COMMENT '用户',
score_type char(1)  DEFAULT NULL COMMENT '分类（0问题 1奖励）',
real_time datetime DEFAULT NULL COMMENT '时间点',
content varchar(500)  DEFAULT NULL COMMENT '学习情况',
`status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1关闭）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(255)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='学习情况记录表';


--
-- Table structure for table `bus_test`
--

DROP TABLE IF EXISTS bus_test;


CREATE TABLE bus_test (
test_id int NOT NULL AUTO_INCREMENT COMMENT '考试批次ID',
test_name varchar(200)  NOT NULL COMMENT '考试批次',
`status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1关闭）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(255)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (test_id) USING BTREE,
UNIQUE KEY uk_name (test_name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='考试批次';


--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS gen_table;


CREATE TABLE gen_table (
table_id bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
`table_name` varchar(200)  DEFAULT '' COMMENT '表名称',
table_comment varchar(500)  DEFAULT '' COMMENT '表描述',
sub_table_name varchar(64)  DEFAULT NULL COMMENT '关联子表的表名',
sub_table_fk_name varchar(64)  DEFAULT NULL COMMENT '子表关联的外键名',
class_name varchar(100)  DEFAULT '' COMMENT '实体类名称',
tpl_category varchar(200)  DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
tpl_web_type varchar(30)  DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
package_name varchar(100)  DEFAULT NULL COMMENT '生成包路径',
module_name varchar(30)  DEFAULT NULL COMMENT '生成模块名',
business_name varchar(30)  DEFAULT NULL COMMENT '生成业务名',
function_name varchar(50)  DEFAULT NULL COMMENT '生成功能名',
function_author varchar(50)  DEFAULT NULL COMMENT '生成功能作者',
gen_type char(1)  DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
gen_path varchar(200)  DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
`options` varchar(1000)  DEFAULT NULL COMMENT '其它生成选项',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(500)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (table_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成业务表';


--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS gen_table_column;


CREATE TABLE gen_table_column (
column_id bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
table_id bigint DEFAULT NULL COMMENT '归属表编号',
`column_name` varchar(200)  DEFAULT NULL COMMENT '列名称',
column_comment varchar(500)  DEFAULT NULL COMMENT '列描述',
column_type varchar(100)  DEFAULT NULL COMMENT '列类型',
java_type varchar(500)  DEFAULT NULL COMMENT 'JAVA类型',
java_field varchar(200)  DEFAULT NULL COMMENT 'JAVA字段名',
is_pk char(1)  DEFAULT NULL COMMENT '是否主键（1是）',
is_increment char(1)  DEFAULT NULL COMMENT '是否自增（1是）',
is_required char(1)  DEFAULT NULL COMMENT '是否必填（1是）',
is_insert char(1)  DEFAULT NULL COMMENT '是否为插入字段（1是）',
is_edit char(1)  DEFAULT NULL COMMENT '是否编辑字段（1是）',
is_list char(1)  DEFAULT NULL COMMENT '是否列表字段（1是）',
is_query char(1)  DEFAULT NULL COMMENT '是否查询字段（1是）',
query_type varchar(200)  DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
html_type varchar(200)  DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
dict_type varchar(200)  DEFAULT '' COMMENT '字典类型',
sort int DEFAULT NULL COMMENT '排序',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (column_id)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成业务表字段';


--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS sys_config;


CREATE TABLE sys_config (
config_id int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
config_name varchar(100)  DEFAULT '' COMMENT '参数名称',
config_key varchar(100)  DEFAULT '' COMMENT '参数键名',
config_value varchar(500)  DEFAULT '' COMMENT '参数键值',
config_type char(1)  DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(500)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (config_id)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='参数配置表';


--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS sys_dept;


CREATE TABLE sys_dept (
dept_id bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
parent_id bigint DEFAULT '0' COMMENT '父部门id',
ancestors varchar(50)  DEFAULT '' COMMENT '祖级列表',
dept_name varchar(30)  DEFAULT '' COMMENT '部门名称',
order_num int DEFAULT '0' COMMENT '显示顺序',
leader varchar(20)  DEFAULT NULL COMMENT '负责人',
phone varchar(11)  DEFAULT NULL COMMENT '联系电话',
email varchar(50)  DEFAULT NULL COMMENT '邮箱',
`status` char(1)  DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
del_flag char(1)  DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (dept_id)
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门表';


--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS sys_dict_data;


CREATE TABLE sys_dict_data (
dict_code bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
dict_sort int DEFAULT '0' COMMENT '字典排序',
dict_label varchar(100)  DEFAULT '' COMMENT '字典标签',
dict_value varchar(100)  DEFAULT '' COMMENT '字典键值',
dict_type varchar(100)  DEFAULT '' COMMENT '字典类型',
css_class varchar(100)  DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
list_class varchar(100)  DEFAULT NULL COMMENT '表格回显样式',
is_default char(1)  DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
`status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1停用）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(500)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (dict_code)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典数据表';


--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS sys_dict_type;


CREATE TABLE sys_dict_type (
dict_id bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
dict_name varchar(100)  DEFAULT '' COMMENT '字典名称',
dict_type varchar(100)  DEFAULT '' COMMENT '字典类型',
`status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1停用）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(500)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (dict_id),
UNIQUE KEY dict_type (dict_type)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典类型表';


--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS sys_job;


CREATE TABLE sys_job (
job_id bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
job_name varchar(64)  NOT NULL DEFAULT '' COMMENT '任务名称',
job_group varchar(64)  NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
invoke_target varchar(500)  NOT NULL COMMENT '调用目标字符串',
cron_expression varchar(255)  DEFAULT '' COMMENT 'cron执行表达式',
misfire_policy varchar(20)  DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
`concurrent` char(1)  DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
`status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1暂停）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(500)  DEFAULT '' COMMENT '备注信息',
PRIMARY KEY (job_id,job_name,job_group)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='定时任务调度表';


--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS sys_job_log;


CREATE TABLE sys_job_log (
job_log_id bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
job_name varchar(64)  NOT NULL COMMENT '任务名称',
job_group varchar(64)  NOT NULL COMMENT '任务组名',
invoke_target varchar(500)  NOT NULL COMMENT '调用目标字符串',
job_message varchar(500)  DEFAULT NULL COMMENT '日志信息',
`status` char(1)  DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
exception_info varchar(2000)  DEFAULT '' COMMENT '异常信息',
create_time datetime DEFAULT NULL COMMENT '创建时间',
PRIMARY KEY (job_log_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='定时任务调度日志表';


--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS sys_logininfor;


CREATE TABLE sys_logininfor (
info_id bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
user_name varchar(50)  DEFAULT '' COMMENT '用户账号',
ipaddr varchar(128)  DEFAULT '' COMMENT '登录IP地址',
login_location varchar(255)  DEFAULT '' COMMENT '登录地点',
browser varchar(50)  DEFAULT '' COMMENT '浏览器类型',
os varchar(50)  DEFAULT '' COMMENT '操作系统',
`status` char(1)  DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
msg varchar(255)  DEFAULT '' COMMENT '提示消息',
login_time datetime DEFAULT NULL COMMENT '访问时间',
PRIMARY KEY (info_id),
KEY idx_sys_logininfor_s (`status`),
KEY idx_sys_logininfor_lt (login_time)
) ENGINE=InnoDB AUTO_INCREMENT=1247 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统访问记录';


--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS sys_menu;


CREATE TABLE sys_menu (
menu_id bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
menu_name varchar(50)  NOT NULL COMMENT '菜单名称',
parent_id bigint DEFAULT '0' COMMENT '父菜单ID',
order_num int DEFAULT '0' COMMENT '显示顺序',
`path` varchar(200)  DEFAULT '' COMMENT '路由地址',
`component` varchar(255)  DEFAULT NULL COMMENT '组件路径',
`query` varchar(255)  DEFAULT NULL COMMENT '路由参数',
route_name varchar(50)  DEFAULT '' COMMENT '路由名称',
is_frame int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
is_cache int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
menu_type char(1)  DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
`visible` char(1)  DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
`status` char(1)  DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
perms varchar(100)  DEFAULT NULL COMMENT '权限标识',
icon varchar(100)  DEFAULT '#' COMMENT '菜单图标',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(500)  DEFAULT '' COMMENT '备注',
PRIMARY KEY (menu_id)
) ENGINE=InnoDB AUTO_INCREMENT=2026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单权限表';


--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS sys_notice;


CREATE TABLE sys_notice (
notice_id int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
notice_title varchar(50)  NOT NULL COMMENT '公告标题',
notice_type char(1)  NOT NULL COMMENT '公告类型（1通知 2公告）',
notice_content longblob COMMENT '公告内容',
`status` char(1)  DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(255)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (notice_id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='通知公告表';


--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS sys_oper_log;


CREATE TABLE sys_oper_log (
oper_id bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
title varchar(50)  DEFAULT '' COMMENT '模块标题',
business_type int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
method varchar(200)  DEFAULT '' COMMENT '方法名称',
request_method varchar(10)  DEFAULT '' COMMENT '请求方式',
operator_type int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
oper_name varchar(50)  DEFAULT '' COMMENT '操作人员',
dept_name varchar(50)  DEFAULT '' COMMENT '部门名称',
oper_url varchar(255)  DEFAULT '' COMMENT '请求URL',
oper_ip varchar(128)  DEFAULT '' COMMENT '主机地址',
oper_location varchar(255)  DEFAULT '' COMMENT '操作地点',
oper_param varchar(2000)  DEFAULT '' COMMENT '请求参数',
json_result varchar(2000)  DEFAULT '' COMMENT '返回参数',
`status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
error_msg varchar(2000)  DEFAULT '' COMMENT '错误消息',
oper_time datetime DEFAULT NULL COMMENT '操作时间',
cost_time bigint DEFAULT '0' COMMENT '消耗时间',
PRIMARY KEY (oper_id),
KEY idx_sys_oper_log_bt (business_type),
KEY idx_sys_oper_log_s (`status`),
KEY idx_sys_oper_log_ot (oper_time)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志记录';


--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS sys_post;


CREATE TABLE sys_post (
post_id bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
post_code varchar(64)  NOT NULL COMMENT '岗位编码',
post_name varchar(50)  NOT NULL COMMENT '岗位名称',
post_sort int NOT NULL COMMENT '显示顺序',
`status` char(1)  NOT NULL COMMENT '状态（0正常 1停用）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(500)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (post_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='岗位信息表';


--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS sys_role;


CREATE TABLE sys_role (
role_id bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
role_name varchar(30)  NOT NULL COMMENT '角色名称',
role_key varchar(100)  NOT NULL COMMENT '角色权限字符串',
role_sort int NOT NULL COMMENT '显示顺序',
data_scope char(1)  DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
menu_check_strictly tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
dept_check_strictly tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
`status` char(1)  NOT NULL COMMENT '角色状态（0正常 1停用）',
del_flag char(1)  DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(500)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色信息表';


--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS sys_role_dept;


CREATE TABLE sys_role_dept (
role_id bigint NOT NULL COMMENT '角色ID',
dept_id bigint NOT NULL COMMENT '部门ID',
PRIMARY KEY (role_id,dept_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色和部门关联表';


--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS sys_role_menu;


CREATE TABLE sys_role_menu (
role_id bigint NOT NULL COMMENT '角色ID',
menu_id bigint NOT NULL COMMENT '菜单ID',
PRIMARY KEY (role_id,menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色和菜单关联表';


--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS sys_user;


CREATE TABLE sys_user (
user_id bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
dept_id bigint DEFAULT NULL COMMENT '部门ID',
user_name varchar(30)  NOT NULL COMMENT '用户账号',
nick_name varchar(30)  NOT NULL COMMENT '用户昵称',
user_type varchar(2)  DEFAULT '00' COMMENT '用户类型（00系统用户）',
email varchar(50)  DEFAULT '' COMMENT '用户邮箱',
phonenumber varchar(11)  DEFAULT '' COMMENT '手机号码',
sex char(1)  DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
avatar varchar(100)  DEFAULT '' COMMENT '头像地址',
`password` varchar(100)  DEFAULT '' COMMENT '密码',
`status` char(1)  DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
del_flag char(1)  DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
login_ip varchar(128)  DEFAULT '' COMMENT '最后登录IP',
login_date datetime DEFAULT NULL COMMENT '最后登录时间',
create_by varchar(64)  DEFAULT '' COMMENT '创建者',
create_time datetime DEFAULT NULL COMMENT '创建时间',
update_by varchar(64)  DEFAULT '' COMMENT '更新者',
update_time datetime DEFAULT NULL COMMENT '更新时间',
remark varchar(500)  DEFAULT NULL COMMENT '备注',
PRIMARY KEY (user_id),
KEY idx_username (user_name) USING BTREE,
KEY idx_nickname (nick_name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1091 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';


--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS sys_user_post;


CREATE TABLE sys_user_post (
user_id bigint NOT NULL COMMENT '用户ID',
post_id bigint NOT NULL COMMENT '岗位ID',
PRIMARY KEY (user_id,post_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户与岗位关联表';


--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS sys_user_role;


CREATE TABLE sys_user_role (
user_id bigint NOT NULL COMMENT '用户ID',
role_id bigint NOT NULL COMMENT '角色ID',
PRIMARY KEY (user_id,role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户和角色关联表';

CREATE TABLE `wx_business` (
    `business_id` bigint NOT NULL COMMENT '商业ID',
    `business_code` varchar(50)  NOT NULL COMMENT '商业编码',
    `business_name` varchar(250)  NOT NULL COMMENT '商业名称',
    `status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1关闭）',
    `create_by` varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255)  DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`business_id`) USING BTREE,
    UNIQUE KEY `uk_businesscode` (`business_code`)
) ENGINE=InnoDB COMMENT='微信表';

CREATE TABLE `wx_media` (
    `media_id` bigint NOT NULL AUTO_INCREMENT COMMENT '媒体ID',
    `business_id` bigint DEFAULT NULL COMMENT '商业ID',
    `media_name` varchar(50)  DEFAULT NULL COMMENT '媒体名称',
    `media_path` varchar(100)  DEFAULT NULL COMMENT '媒体路径',
    `status` char(1)  DEFAULT '0' COMMENT '状态（0正常 1关闭）',
    `create_by` varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255)  DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`media_id`) USING BTREE
) ENGINE=InnoDB COMMENT='微信媒体表';