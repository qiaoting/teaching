import request from '@/utils/request'

export function listTeacher() {
  return request({
    url: '/course/schedule/listTeacher',
    method: 'get',
    params: null
  })
}

// 查询课程列表
export function listSchedule(query) {
  return request({
    url: '/course/schedule/list',
    method: 'get',
    params: query
  })
}

// 查询课程详细
export function getSchedule(scheduleId) {
  return request({
    url: '/course/schedule/' + scheduleId,
    method: 'get'
  })
}

// 新增课程
export function addSchedule(data) {
  return request({
    url: '/course/schedule',
    method: 'post',
    data: data
  })
}

// 修改课程
export function updateSchedule(data) {
  return request({
    url: '/course/schedule',
    method: 'put',
    data: data
  })
}

// 删除课程
export function delSchedule(scheduleId) {
  return request({
    url: '/course/schedule/' + scheduleId,
    method: 'delete'
  })
}
