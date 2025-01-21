import request from '@/utils/request'

// 查询考试批次列表
export function listTest(query) {
  return request({
    url: '/teaching/test/list',
    method: 'get',
    params: query
  })
}

// 查询考试批次详细
export function getTest(testId) {
  return request({
    url: '/teaching/test/' + testId,
    method: 'get'
  })
}

// 新增考试批次
export function addTest(data) {
  return request({
    url: '/teaching/test',
    method: 'post',
    data: data
  })
}

// 修改考试批次
export function updateTest(data) {
  return request({
    url: '/teaching/test',
    method: 'put',
    data: data
  })
}

// 删除考试批次
export function delTest(testId) {
  return request({
    url: '/teaching/test/' + testId,
    method: 'delete'
  })
}
