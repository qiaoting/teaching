import request from '@/utils/request'


// 查询学习情况记录列表
export function listStudy(query) {
  return request({
    url: '/teaching/study/list',
    method: 'get',
    params: query
  })
}

// 查询学习情况记录详细
export function getStudy(id) {
  return request({
    url: '/teaching/study/' + id,
    method: 'get'
  })
}

// 新增学习情况记录
export function addStudy(data) {
  return request({
    url: '/teaching/study',
    method: 'post',
    data: data
  })
}

// 修改学习情况记录
export function updateStudy(data) {
  return request({
    url: '/teaching/study',
    method: 'put',
    data: data
  })
}

// 删除学习情况记录
export function delStudy(id) {
  return request({
    url: '/teaching/study/' + id,
    method: 'delete'
  })
}
