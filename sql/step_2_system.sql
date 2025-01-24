DROP TABLE IF EXISTS `bus_course`;
CREATE TABLE `bus_course` (
    `course_id` int NOT NULL AUTO_INCREMENT COMMENT '课程',
    `course_name` varchar(250) NOT NULL COMMENT '课程名称',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1关闭）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`course_id`) USING BTREE,
    UNIQUE KEY `uk_coursename` (`course_name`)
) ENGINE=InnoDB COMMENT='课程表';

DROP TABLE IF EXISTS `bus_schedule`;
CREATE TABLE `bus_schedule` (
    `schedule_id` int NOT NULL AUTO_INCREMENT COMMENT '课程表',
    `schedule_name` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程表名称',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1关闭）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`schedule_id`),
    UNIQUE KEY `uk_name` (`schedule_name`)
) ENGINE=InnoDB COMMENT='课程表';

DROP TABLE IF EXISTS `bus_schedule_detail`;
CREATE TABLE `bus_schedule_detail` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `schedule_id` bigint NOT NULL COMMENT '课程表',
    `course_id` int DEFAULT NULL COMMENT '课程',
    `dept_id` bigint DEFAULT NULL COMMENT '班级',
    `teacher_id` bigint DEFAULT NULL COMMENT '教师',
    `day_week` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '周几',
    `hour_day` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第几节课',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1关闭）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='课表明细';

DROP TABLE IF EXISTS `bus_score`;
CREATE TABLE `bus_score` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `test_id` int DEFAULT NULL COMMENT '考试批次',
    `student_id` bigint DEFAULT NULL COMMENT '学号',
    `course_id` int DEFAULT NULL COMMENT '课程',
    `score` float DEFAULT NULL COMMENT '成绩',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1关闭）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='成绩表';

DROP TABLE IF EXISTS `bus_stu_study`;
CREATE TABLE `bus_stu_study` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `course_id` int DEFAULT NULL COMMENT '课程',
    `dept_id` bigint DEFAULT NULL COMMENT '部门',
    `user_id` bigint DEFAULT NULL COMMENT '用户',
    `score_type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分类（0问题 1奖励）',
    `real_time` datetime DEFAULT NULL COMMENT '时间点',
    `content` varchar(500) DEFAULT NULL COMMENT '学习情况',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1关闭）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='学习情况记录表';

DROP TABLE IF EXISTS `bus_test`;
CREATE TABLE `bus_test` (
    `test_id` int NOT NULL AUTO_INCREMENT COMMENT '考试批次ID',
    `test_name` varchar(200) NOT NULL COMMENT '考试批次',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1关闭）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`test_id`) USING BTREE,
    UNIQUE KEY `uk_name` (`test_name`) USING BTREE
) ENGINE=InnoDB COMMENT='考试批次';

INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (103, '周几', 'bus_day_week', '0', 'admin', '2025-01-12 08:55:40', 'admin', '2025-01-12 16:20:28', NULL);
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (104, '课节', 'bus_hour_day', '0', 'admin', '2025-01-12 08:57:48', 'admin', '2025-01-12 16:20:33', NULL);
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (106, '学习情况', 'bus_study_status', '0', 'admin', '2025-01-13 13:17:07', '', NULL, NULL);

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (116, 1, '星期一', '1', 'bus_day_week', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:56:09', 'admin', '2025-01-15 08:59:47', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (117, 2, '星期二', '2', 'bus_day_week', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:56:22', 'admin', '2025-01-15 08:59:54', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (118, 3, '星期三', '3', 'bus_day_week', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:56:35', 'admin', '2025-01-15 08:59:57', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (119, 4, '星期四', '4', 'bus_day_week', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:56:46', 'admin', '2025-01-15 09:00:03', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (120, 5, '星期五', '5', 'bus_day_week', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:56:59', 'admin', '2025-01-15 09:00:07', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (121, 6, '星期六', '6', 'bus_day_week', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:57:10', 'admin', '2025-01-15 09:00:11', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (122, 7, '星期天', '7', 'bus_day_week', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:57:22', 'admin', '2025-01-15 09:00:15', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (123, 1, '第一节', '1', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:58:04', 'admin', '2025-01-12 13:29:35', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (124, 2, '第二节', '2', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:58:14', 'admin', '2025-01-12 13:29:58', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (125, 3, '第三节', '3', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:58:25', 'admin', '2025-01-12 13:30:02', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (126, 4, '第四节', '4', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:58:36', 'admin', '2025-01-12 13:30:09', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (127, 5, '第五节', '5', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:58:47', 'admin', '2025-01-12 13:30:13', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (128, 6, '第六节', '6', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:58:59', 'admin', '2025-01-12 13:30:17', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (129, 7, '第七节', '7', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:59:13', 'admin', '2025-01-12 13:30:20', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (130, 8, '第八节', '8', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:59:25', 'admin', '2025-01-12 13:30:25', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (131, 9, '第九节', '9', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:59:39', 'admin', '2025-01-12 13:30:31', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (132, 10, '第十节', '10', 'bus_hour_day', NULL, 'default', 'N', '0', 'admin', '2025-01-12 08:59:56', 'admin', '2025-01-12 13:30:36', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (137, 0, '2024下学期最后一次课表', '2024_last', 'schedule_20250112', NULL, 'default', 'N', '0', 'admin', '2025-01-12 09:40:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (143, 1, '加分项', '1', 'bus_study_status', NULL, 'success', 'N', '0', 'admin', '2025-01-13 13:17:52', 'admin', '2025-01-15 09:00:46', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (145, 2, '电脑玩游戏', '2', 'bus_study_status', NULL, 'danger', 'N', '0', 'admin', '2025-01-13 13:18:36', 'admin', '2025-01-15 09:00:50', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (146, 3, '睡觉', '3', 'bus_study_status', NULL, 'danger', 'N', '0', 'admin', '2025-01-13 13:18:46', 'admin', '2025-01-15 09:00:54', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (147, 4, '手机玩游戏', '4', 'bus_study_status', NULL, 'danger', 'N', '0', 'admin', '2025-01-13 13:19:12', 'admin', '2025-01-15 09:51:35', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (148, 5, '手机其他', '5', 'bus_study_status', NULL, 'warning', 'N', '0', 'admin', '2025-01-13 13:24:47', 'admin', '2025-01-15 09:51:47', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (152, 6, '不交作业', '6', 'bus_study_status', NULL, 'warning', 'N', '0', 'admin', '2025-01-15 08:35:50', 'admin', '2025-01-15 09:01:03', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (153, 7, '其他', '7', 'bus_study_status', NULL, 'warning', 'N', '0', 'admin', '2025-01-15 08:56:43', 'admin', '2025-01-15 09:01:07', NULL);

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2000, '教学管理', 0, 1, 'teaching', NULL, NULL, '', 1, 0, 'M', '0', '0', '', 'redis-list', 'admin', '2025-01-11 10:30:03', 'admin', '2025-01-19 13:48:31', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2006, '日常记录', 2000, 1, 'study', 'teaching/study/index', NULL, '', 1, 0, 'C', '0', '0', '', 'edit', 'admin', '2025-01-14 10:51:13', 'admin', '2025-01-19 13:50:57', '学习情况记录菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2016, '点名', 2000, 2, 'orderutil', 'teaching/orderutil/index', NULL, '', 1, 0, 'C', '0', '0', '', 'button', 'admin', '2025-01-15 16:28:22', 'admin', '2025-01-19 13:51:13', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2018, '课程管理', 0, 2, 'course', NULL, NULL, '', 1, 0, 'M', '0', '0', '', 'education', 'admin', '2025-01-19 13:48:57', 'admin', '2025-01-19 13:56:56', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2019, '课时', 2018, 2, 'schedule', 'course/schedule/index', NULL, '', 1, 0, 'C', '0', '0', '', 'time-range', 'admin', '2025-01-19 13:50:38', 'admin', '2025-01-19 14:47:26', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2020, '课程', 2018, 1, 'course', 'course/course/index', NULL, '', 1, 0, 'C', '0', '0', NULL, 'excel', 'admin', '2025-01-19 14:07:36', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2021, '考试批次', 2000, 3, 'test', 'teaching/test/index', NULL, '', 1, 0, 'C', '0', '0', 'teaching:test:list', 'skill', 'admin', '2025-01-20 09:57:17', 'admin', '2025-01-20 09:59:28', '考试批次菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2022, '成绩', 2000, 4, 'score', 'teaching/score/index', NULL, '', 1, 0, 'C', '0', '0', 'teaching:score:list', 'list', 'admin', '2025-01-20 10:15:33', 'admin', '2025-01-20 10:15:58', '成绩菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2023, '作业布置', 2000, 5, 'homework', 'teaching/homework/index', NULL, '', 1, 0, 'C', '0', '0', 'teaching:homework:list', 'documentation', 'admin', '2025-01-24 10:38:12', 'admin', '2025-01-24 10:38:51', '作业布置菜单');

